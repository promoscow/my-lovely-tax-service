package ru.xpendence.mylovelytaxservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.xpendence.mylovelytaxservice.transfer.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:48
 * e-mail: v.chernyshov@pflb.ru
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AbstractDto {

    @NotNull(groups = {Validation.New.class, Validation.Login.class})
    @Null(groups = {Validation.Exists.class})
    private String username;

    @NotNull(groups = {Validation.New.class, Validation.Login.class})
    private String password;

    @NotNull(groups = {Validation.New.class})
    private String name;

    @NotNull(groups = {Validation.New.class})
    private String email;

    @NotNull(groups = {Validation.New.class})
    private Long inn;

    @NotNull(groups = {Validation.New.class})
    private Long ogrn;
}
