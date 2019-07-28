package ru.xpendence.mylovelytaxservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.xpendence.mylovelytaxservice.dto.EmailDto;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.dto.ResponseDto;
import ru.xpendence.mylovelytaxservice.entity.Inspection;
import ru.xpendence.mylovelytaxservice.exception.DataBaseException;
import ru.xpendence.mylovelytaxservice.mapper.impl.InspectionMapper;
import ru.xpendence.mylovelytaxservice.repository.InspectionRepository;
import ru.xpendence.mylovelytaxservice.util.InspectionsParser;
import ru.xpendence.mylovelytaxservice.util.TextUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:22
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@Slf4j
public class InspectionServiceImpl implements InspectionService {

    private final InspectionRepository repository;
    private final InspectionMapper mapper;
    private final UserService userService;
    private final RestTemplate restTemplate;

    public InspectionServiceImpl(InspectionMapper mapper,
                                 InspectionRepository repository,
                                 UserService userService,
                                 RestTemplate restTemplate) {
        this.mapper = mapper;
        this.repository = repository;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @Value("${message.sender.path}")
    private String messageSenderPath;

    @Value("${message.sender.port}")
    private String messageSenderPort;

    @Value("${text.rules}")
    private String rulesFile;

    @Value("${text.check}")
    private String checkFile;

    @Override
    public InspectionDto get(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Проверка с ID %d не существует.", id))));
    }

    @Override
    public Boolean getForUser(Long userId) {
        return repository.findByUserInn(userService.get(userId).getInn())
                .stream().anyMatch(i -> i.getStartDate().getMonth().equals(LocalDate.now().getMonth())
                        || i.getStartDate().plusDays(3L).getMonth().equals(LocalDate.now().getMonth())
                );
    }

    @Override
    public List<String> getRules() {
        return TextUtils.getText(rulesFile);
    }

    @Override
    public List<String> getCheck() {
        return TextUtils.getText(checkFile);
    }

    @Override
    @Scheduled(initialDelay = 5000L, fixedDelay = 604800000L)
    public void parse() {
        List<Inspection> inspections = InspectionsParser.parse();
        if (inspections != null && !inspections.isEmpty()) {
            List<Inspection> saved = repository.saveAll(inspections);
            log.info("Inspections saved: {}", saved.size());
            if (saved.isEmpty() || inspections.size() != saved.size()) {
                throw new DataBaseException("Ошибка обновления справочника проверок.");
            }
        }
    }

    @Scheduled(initialDelay = 604800000L, fixedDelay = 604800000L)
    public void inform() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1);
        LocalDate finish = now.withDayOfMonth(now.lengthOfMonth());
        List<String> emailToInform = userService.getEmailToInform(start, finish);
        emailToInform
                .forEach(
                        e -> restTemplate.postForObject(
                                String.format("http://%s:%s/email", messageSenderPath, messageSenderPort),
                                new EmailDto(
                                        e,
                                        "slava_rossii@list.ru",
                                        "У Вас проверка в этом месяце!",
                                        "Да-да, берегитесь! Придут!"
                                ),
                                ResponseDto.class
                        )
                );
    }
}
