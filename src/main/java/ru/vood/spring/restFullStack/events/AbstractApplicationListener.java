package ru.vood.spring.restFullStack.events;

import org.springframework.context.ApplicationListener;


public interface AbstractApplicationListener<M extends ParamEvent, E extends AbstractApplicationEvent<Object, M>> extends ApplicationListener<E> {
}
