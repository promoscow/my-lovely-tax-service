package ru.xpendence.mylovelytaxservice.service;

import ru.xpendence.mylovelytaxservice.dto.InspectionDto;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:21
 * e-mail: v.chernyshov@pflb.ru
 */
public interface InspectionService {

    InspectionDto get(Long id);

    Boolean getForUser(Long userId);

    void parse();
}
