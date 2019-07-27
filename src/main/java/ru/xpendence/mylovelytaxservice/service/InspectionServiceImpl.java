package ru.xpendence.mylovelytaxservice.service;

import org.springframework.stereotype.Service;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.dto.UserDto;
import ru.xpendence.mylovelytaxservice.exception.DataBaseException;
import ru.xpendence.mylovelytaxservice.mapper.impl.InspectionMapper;
import ru.xpendence.mylovelytaxservice.repository.InspectionRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:22
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
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
    public List<InspectionDto> getForUser(Long userId) {
        UserDto userDto = userService.get(userId);
        return repository.findByUserInn(userDto.getInn())
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
