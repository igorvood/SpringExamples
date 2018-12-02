package ru.vood.spring.restFullStack.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.spring.restFullStack.common.intf.CommonFunctionService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static ru.vood.spring.restFullStack.consts.Const.SCHEMA;

@Service
@Transactional
public class CommonFunction implements CommonFunctionService {

    private final EntityManager entityManager;

    public CommonFunction(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BigDecimal nextId(String sequenceName) {
        final Object o = entityManager
                .createNativeQuery("SELECT " + SCHEMA + "." + sequenceName + ".nextval from dual")
                .getResultList().get(0);
        return (BigDecimal) o;
    }


}
