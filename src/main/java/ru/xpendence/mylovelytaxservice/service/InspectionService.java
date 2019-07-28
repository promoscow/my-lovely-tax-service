package ru.xpendence.mylovelytaxservice.service;

import ru.xpendence.mylovelytaxservice.dto.InspectionDto;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:21
 * e-mail: v.chernyshov@pflb.ru
 */
public interface InspectionService {

    InspectionDto get(Long id);

    Boolean getForUser(Long userId);

    List<String> getRules();

    List<String> getCheck();

    void parse();
}
