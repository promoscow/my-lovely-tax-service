package ru.xpendence.mylovelytaxservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.xpendence.mylovelytaxservice.dto.UserDto;
import ru.xpendence.mylovelytaxservice.entity.User;
import ru.xpendence.mylovelytaxservice.exception.DataBaseException;
import ru.xpendence.mylovelytaxservice.mapper.impl.UserMapper;
import ru.xpendence.mylovelytaxservice.repository.UserRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:53
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto create(UserDto dto) throws JsonProcessingException {
        UserDto userDto = mapper.toDto(repository.save(mapper.toEntity(dto)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDto update(UserDto dto) throws JsonProcessingException {
        User user = repository.findById(dto.getId())
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", dto.getId())));
        UserDto userDto = mapper.toDto(repository.save(mapper.toEntity(dto, user)));
        log.info("New User saved: {}", new ObjectMapper().writeValueAsString(userDto));
        return userDto;
    }

    @Override
    public UserDto get(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new DataBaseException(String.format("Пользователь с ID %d не существует.", id))));
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        boolean deleted = !repository.findById(id).isPresent();
        if (deleted) {
            log.info("User deleted by ID: {}", id);
        }
        return deleted;
    }
}
