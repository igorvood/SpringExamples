package ru.vood.spring.restFullStack.controller.validation.intf;


import ru.vood.spring.restFullStack.wrapRequest.ErrorMessage;

public interface UsrRestControllerValidationInterface {

    ErrorMessage findOne(Long id);
}
