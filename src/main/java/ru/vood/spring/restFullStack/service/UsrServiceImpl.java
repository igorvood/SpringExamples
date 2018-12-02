package ru.vood.spring.restFullStack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.spring.restFullStack.entity.UsrEntity;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.entity.dto.convert.UsrConvertService;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.repository.intf.UsrRepository;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.service.intf.UsrService;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForService;

import java.util.List;
import java.util.function.Function;

@Service
@Transactional
public class UsrServiceImpl implements UsrService {

    private final UsrRepository usrRepository;

    private final WrapperForService wrapper;

    private final UsrConvertService usrConvertService;

    @Autowired
    public UsrServiceImpl(UsrRepository usrRepository, WrapperForService wrapper, UsrConvertService usrConvertService) {
        this.usrRepository = usrRepository;
        this.wrapper = wrapper;
        this.usrConvertService = usrConvertService;
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperForService.WrappedObjectForService<UsrDTO> findOne(Long id) {
        return wrapper.wrapObject(first(usrRepository::findOne)
                .andThen(usrConvertService::convertToDto), id);
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperForService.WrappedObjectForService<UsrDTO> findAllPage(Integer numPage, Integer sizePage) {
        return wrapper.wrapList(first(usrRepository::findAllPage)
                        .andThen(usrConvertService::convertToDto)
                , numPage, sizePage);
    }


    @Override
    @Transactional(readOnly = true)
    public WrapperForService.WrappedObjectForService<User> findAllFilteredLimit(SearchData searchData) {
        return wrapper.wrapList(first(usrRepository::findAllFilteredLimit)
                , searchData);
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperForService.WrappedObjectForService<UsrDTO> findAllLimit(Integer limit) {
        return wrapper.wrapList(first(usrRepository::findAllLimit)
                        .andThen(usrConvertService::convertToDto)
                , limit);
    }

    @Override
    public WrapperForService.WrappedObjectForService<UsrDTO> save(UsrDTO entity) {
        final Function<UsrDTO, UsrEntity> objectObjectFunction = usrConvertService::convertToEntity;
        final Function<UsrDTO, List<UsrDTO>> usrDTOListFunction = first(objectObjectFunction)
                .andThen(usrRepository::save)
                .andThen(usrConvertService::convertToDto);
        return wrapper.wrapList(usrDTOListFunction, entity);
    }

    @Override
    public WrapperForService.WrappedObjectForService<UsrDTO> findByLogin(String id) {
        return wrapper.wrapObject(first(usrRepository::findByLogin)
                        .andThen(usrConvertService::convertToDto)
                , id);
    }

    @Override
    public WrapperForService.WrappedObjectForService<UsrDTO> findAll() {
        return wrapper.wrapList(first(usrRepository::findAll)
                        .andThen(usrConvertService::convertToDto)
                , null);
    }
}
