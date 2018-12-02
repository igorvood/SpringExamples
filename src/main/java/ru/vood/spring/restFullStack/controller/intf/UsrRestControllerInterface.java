package ru.vood.spring.restFullStack.controller.intf;

import ru.vood.spring.restFullStack.common.intf.BeginnerOfChainFunctionInterface;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForController;

public interface UsrRestControllerInterface<DTO extends UsrDTO> extends BeginnerOfChainFunctionInterface {

    WrapperForController.WrappedObjectForRest<DTO> findOne(Long id);

    WrapperForController.WrappedObjectForRest<DTO> findAllPage(Integer numPage, Integer sizePage);

    WrapperForController.WrappedObjectForRest<User> findAllFilteredLimit(SearchData searchData);

    WrapperForController.WrappedObjectForRest<DTO> findAllLimit(Integer limit);

    WrapperForController.WrappedObjectForRest<DTO> save(DTO entity);

}
