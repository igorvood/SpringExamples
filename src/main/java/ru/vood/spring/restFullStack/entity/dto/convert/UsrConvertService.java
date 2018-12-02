package ru.vood.spring.restFullStack.entity.dto.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.vood.spring.restFullStack.entity.UsrEntity;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;

@Component
public class UsrConvertService extends AbstractConvert<UsrDTO, UsrEntity> {

    public UsrConvertService(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UsrDTO convertToDto(UsrEntity usrEntity) {
        final UsrDTO map = modelMapper.map(usrEntity, UsrDTO.class);
        return map;
    }

    @Override
    public UsrEntity convertToEntity(UsrDTO entity) {
        return modelMapper.map(entity, UsrEntity.class);
    }


}
