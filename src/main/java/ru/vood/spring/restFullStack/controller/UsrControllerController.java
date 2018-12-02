package ru.vood.spring.restFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vood.spring.restFullStack.controller.intf.UsrRestControllerInterface;
import ru.vood.spring.restFullStack.controller.validation.intf.UsrRestControllerValidationInterface;
import ru.vood.spring.restFullStack.entity.dto.UsrDTO;
import ru.vood.spring.restFullStack.repository.filterData.SearchData;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.User;
import ru.vood.spring.restFullStack.service.intf.UsrService;
import ru.vood.spring.restFullStack.wrapRequest.WrapperForController;

@RestController
public class UsrControllerController implements UsrRestControllerInterface, WrapperForController {

    private final UsrService usrService;

    private final UsrRestControllerValidationInterface validationInterface;

    @Autowired
    public UsrControllerController(UsrService usrService, UsrRestControllerValidationInterface validationInterface) {
        this.usrService = usrService;
        this.validationInterface = validationInterface;
    }

    @Override
    @GetMapping(value = "usr/findOne")
    public WrappedObject<UsrDTO> findOne(@RequestParam(value = "id") Long id) {
        return validateAndWrapObject(validationInterface::findOne, first(usrService::findOne), id);
    }

    @Override
    @GetMapping(value = "usr/findAllPageRequest")
    public WrappedObject<UsrDTO> findAllPage(
            @RequestParam(value = "numPage", defaultValue = "1", required = false) Integer numPage,
            @RequestParam(value = "sizePage", defaultValue = "20", required = false) Integer sizePage) {
        return wrapObject(first(usrService::findAllPage), numPage, sizePage);
    }

    @Override
    @PostMapping(value = "usr/findAllLimit")
    public WrappedObject<User> findAllFilteredLimit(
            @RequestBody SearchData searchData) {
        return wrapObject(first(usrService::findAllFilteredLimit), searchData);
    }

    @Override
    @GetMapping(value = "usr/findAllLimit")
    public WrappedObject<UsrDTO> findAllLimit(@RequestParam(value = "limit", defaultValue = "100", required = false) Integer limit) {
        return wrapObject(first(usrService::findAllLimit), limit);
    }

    @Override
    @PutMapping(value = "usr/save")
    public @ResponseBody
    WrappedObject<UsrDTO> save(@RequestBody UsrDTO entity) {
        return wrapObject(first(usrService::save), entity);
    }
}
