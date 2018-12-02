package ru.vood.spring.restFullStack.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AppContext {

    private static EntityManager manager;
    private static ApplicationContext applicationContext;

    public static EntityManager getManager() {
        return manager;
    }

    @Autowired
    private void setManager(EntityManager manager) {
        AppContext.manager = manager;
    }

    public static <T> T getService(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public ApplicationContext getAppContext() {
        return applicationContext;
    }

    @Autowired
    private void ApplicationContextHolder(ApplicationContext applicationContext) {
        AppContext.applicationContext = applicationContext;
    }


}
