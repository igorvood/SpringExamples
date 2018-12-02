package ru.vood.spring.restFullStack.controller.validation;

import org.springframework.stereotype.Component;
import ru.vood.spring.restFullStack.controller.validation.intf.UsrRestControllerValidationInterface;
import ru.vood.spring.restFullStack.wrapRequest.ErrorMessage;

import static java.util.Arrays.asList;

@Component
public class UsrRestControllerValidation implements UsrRestControllerValidationInterface {

    @Override
    public ErrorMessage findOne(Long id) {
        if (id == null || id < 0)
            return ErrorMessage.builder()
                    .errorMessages(asList("Не заполнен параметр id"))
                    .build();
        return new ErrorMessage();
    }
}
