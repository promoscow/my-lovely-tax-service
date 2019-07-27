package ru.xpendence.mylovelytaxservice.service;

import ru.xpendence.mylovelytaxservice.dto.UserDto;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 21:50
 * e-mail: v.chernyshov@pflb.ru
 */
public interface LoginService {

    UserDto login(UserDto dto);
}
