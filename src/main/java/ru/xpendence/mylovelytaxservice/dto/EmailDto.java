package ru.xpendence.mylovelytaxservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 20:43
 * e-mail: v.chernyshov@pflb.ru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String to;
    private String from;
    private String subject;
    private String text;
}
