package ru.vood.spring.restFullStack.service.intf;

import ru.vood.spring.restFullStack.common.intf.BeginnerOfChainFunctionInterface;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForService;


public interface UsrService extends BeginnerOfChainFunctionInterface {

    WrapperForService.WrappedObjectForService<UsrDTO> findOne(Long id);

    WrapperForService.WrappedObjectForService<UsrDTO> findAllPage(Integer numPage, Integer sizePage);

    WrapperForService.WrappedObjectForService<User> findAllFilteredLimit(SearchData searchData);

    WrapperForService.WrappedObjectForService<UsrDTO> findAllLimit(Integer limit);

    WrapperForService.WrappedObjectForService<UsrDTO> save(UsrDTO entity);

    WrapperForService.WrappedObjectForService<UsrDTO> findByLogin(String id);

    WrapperForService.WrappedObjectForService<UsrDTO> findAll();

}
