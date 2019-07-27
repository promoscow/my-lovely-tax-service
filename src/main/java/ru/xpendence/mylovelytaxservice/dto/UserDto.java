package ru.xpendence.mylovelytaxservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:48
 * e-mail: v.chernyshov@pflb.ru
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AbstractDto {

    private String username;
    private String password;
    private String name;
}
