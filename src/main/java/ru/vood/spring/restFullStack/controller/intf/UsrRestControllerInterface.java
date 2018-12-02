package ru.vood.spring.restFullStack.controller.intf;

import ru.vood.spring.restFullStack.common.intf.BeginnerOfChainFunctionInterface;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForController;

public interface UsrRestControllerInterface<DTO extends UsrDTO> extends BeginnerOfChainFunctionInterface {

    WrapperForController.WrappedObject<DTO> findOne(Long id);

    WrapperForController.WrappedObject<DTO> findAllPage(Integer numPage, Integer sizePage);

    WrapperForController.WrappedObject<User> findAllFilteredLimit(SearchData searchData);

    WrapperForController.WrappedObject<DTO> findAllLimit(Integer limit);

    WrapperForController.WrappedObject<DTO> save(DTO entity);

}
