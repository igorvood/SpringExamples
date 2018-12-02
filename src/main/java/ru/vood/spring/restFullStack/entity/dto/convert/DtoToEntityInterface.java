package ru.vood.spring.restFullStack.entity.dto.convert;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoToEntityInterface<DTO, ENTITY> {

    DTO convertToDto(ENTITY entity);

    ENTITY convertToEntity(DTO entity);

    default List<DTO> convertToDto(List<ENTITY> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    default List<ENTITY> convertToEntity(List<DTO> entity) {
        return entity.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

}
