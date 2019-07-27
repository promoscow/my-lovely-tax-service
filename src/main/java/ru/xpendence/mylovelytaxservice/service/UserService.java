package ru.xpendence.mylovelytaxservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.xpendence.mylovelytaxservice.dto.UserDto;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:51
 * e-mail: v.chernyshov@pflb.ru
 */
public interface UserService {

    UserDto create(UserDto dto) throws JsonProcessingException;

    UserDto update(UserDto dto) throws JsonProcessingException;

    UserDto get(Long id);

    Page<UserDto> getAll(Pageable pageable);

    boolean delete(Long id);
}
