package ru.xpendence.mylovelytaxservice.mapper;

import ru.xpendence.mylovelytaxservice.dto.AbstractDto;
import ru.xpendence.mylovelytaxservice.entity.AbstractEntity;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.02.19
 * Time: 21:53
 * e-mail: 2262288@gmail.com
 */
public interface EntityDtoMapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    E toEntity(D dto, E entity);

    D toDto(E entity);

    D toDto(E entity, D dto);
}
