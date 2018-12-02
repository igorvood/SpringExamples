package ru.vood.spring.restFullStack.entity.seqGenerators;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import ru.vood.spring.restFullStack.common.AppContext;
import ru.vood.spring.restFullStack.common.intf.CommonFunctionService;

import java.io.Serializable;


public class UsrSequence implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return AppContext.getService(CommonFunctionService.class).nextId("USR_SEQ").longValue();
    }
}
