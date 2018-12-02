package ru.vood.spring.restFullStack.common.intf;

import java.math.BigDecimal;

public interface CommonFunctionService {

    BigDecimal nextId(String sequenceName);
}
