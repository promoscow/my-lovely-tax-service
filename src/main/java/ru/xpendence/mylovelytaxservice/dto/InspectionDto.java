package ru.xpendence.mylovelytaxservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:19
 * e-mail: v.chernyshov@pflb.ru
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InspectionDto extends AbstractDto {

    private String companyName;
    private String officialLocation;
    private Long ogrn;
    private Long inn;
    private String inspectionTarget;
    private String otherReasons;
    private LocalDate startDate;
    private Integer limitationDays;
    private Integer limitationHours;
    private String format;
    private String note;
}
