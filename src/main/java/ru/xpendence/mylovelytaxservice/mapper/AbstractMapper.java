package ru.xpendence.mylovelytaxservice.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.xpendence.mylovelytaxservice.dto.AbstractDto;
import ru.xpendence.mylovelytaxservice.entity.AbstractEntity;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 04.02.19
 * Time: 21:55
 * e-mail: 2262288@gmail.com
 */
public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements EntityDtoMapper<E, D> {

    @Autowired
    protected ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    @PostConstruct
    public void init() {
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public E toEntity(D dto, E entity) {
        if (Objects.isNull(dto)) {
            return entity;
        }
        if (Objects.isNull(entity)) {
            return mapper.map(dto, entityClass);
        }
        mapper.map(dto, entity);
        return entity;
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public D toDto(E entity, D dto) {
        if (Objects.isNull(entity)) {
            return dto;
        }
        if (Objects.isNull(dto)) {
            return mapper.map(entity, dtoClass);
        }
        mapper.map(entity, dto);
        return dto;
    }

    protected Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected void mapSpecificFields(E source, D destination) {
    }

    protected void mapSpecificFields(D source, E destination) {
    }

    protected <T> void whenNotNull(T o, Consumer<T> c) {
        if (Objects.nonNull(o)) {
            c.accept(o);
        }
    }
}
