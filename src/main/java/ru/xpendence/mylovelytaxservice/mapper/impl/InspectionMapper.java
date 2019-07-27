package ru.xpendence.mylovelytaxservice.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.mylovelytaxservice.dto.InspectionDto;
import ru.xpendence.mylovelytaxservice.entity.Inspection;
import ru.xpendence.mylovelytaxservice.mapper.AbstractMapper;
import ru.xpendence.mylovelytaxservice.mapper.Mapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:20
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Inspection.class, dto = InspectionDto.class)
public class InspectionMapper extends AbstractMapper<Inspection, InspectionDto> {
}
