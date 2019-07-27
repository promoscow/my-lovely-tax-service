package ru.xpendence.mylovelytaxservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.entity.Inspection;
import ru.xpendence.mylovelytaxservice.exception.DataBaseException;
import ru.xpendence.mylovelytaxservice.mapper.impl.InspectionMapper;
import ru.xpendence.mylovelytaxservice.repository.InspectionRepository;
import ru.xpendence.mylovelytaxservice.util.InspectionsParser;

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

    public InspectionServiceImpl(InspectionMapper mapper,
                                 InspectionRepository repository,
                                 UserService userService) {
        this.mapper = mapper;
        this.repository = repository;
        this.userService = userService;
    }

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
        System.out.println();
    }
}