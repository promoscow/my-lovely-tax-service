package ru.xpendence.mylovelytaxservice.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.mylovelytaxservice.dto.UserDto;
import ru.xpendence.mylovelytaxservice.entity.User;
import ru.xpendence.mylovelytaxservice.mapper.AbstractMapper;
import ru.xpendence.mylovelytaxservice.mapper.Mapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:57
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = User.class, dto = UserDto.class)
public class UserMapper extends AbstractMapper<User, UserDto> {
}
