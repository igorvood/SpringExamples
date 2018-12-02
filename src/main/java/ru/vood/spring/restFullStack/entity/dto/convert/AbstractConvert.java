package ru.vood.spring.restFullStack.entity.dto.convert;

import org.modelmapper.ModelMapper;

abstract class AbstractConvert<DTO, ENTITY> implements DtoToEntityInterface<DTO, ENTITY> {

    protected final ModelMapper modelMapper;

    public AbstractConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
