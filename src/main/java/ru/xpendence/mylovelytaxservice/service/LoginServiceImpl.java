package ru.xpendence.mylovelytaxservice.service;

import org.springframework.stereotype.Service;
import ru.xpendence.mylovelytaxservice.dto.UserDto;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 21:49
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    public LoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto login(UserDto dto) {
        return userService.getByCredentials(dto.getUsername(), dto.getPassword());
    }
}
