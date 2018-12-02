package ru.vood.spring.restFullStack.service.intf;

import ru.vood.spring.restFullStack.common.intf.BeginnerOfChainFunctionInterface;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForService;


public interface UsrService extends BeginnerOfChainFunctionInterface {

    WrapperForService.WrappedObject<UsrDTO> findOne(Long id);

    WrapperForService.WrappedObject<UsrDTO> findAllPage(Integer numPage, Integer sizePage);

    WrapperForService.WrappedObject<User> findAllFilteredLimit(SearchData searchData);

    WrapperForService.WrappedObject<UsrDTO> findAllLimit(Integer limit);

    WrapperForService.WrappedObject<UsrDTO> save(UsrDTO entity);

    WrapperForService.WrappedObject<UsrDTO> findByLogin(String id);

    WrapperForService.WrappedObject<UsrDTO> findAll();

}
