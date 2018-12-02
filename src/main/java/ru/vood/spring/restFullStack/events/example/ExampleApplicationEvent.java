package ru.vood.spring.restFullStack.events.example;

import ru.vood.spring.restFullStack.events.AbstractApplicationEvent;
import ru.vood.spring.restFullStack.events.ParamEvent;

public class ExampleApplicationEvent extends AbstractApplicationEvent<Object, ParamEvent> {
    public ExampleApplicationEvent(Object source, ParamEvent paramEvent) {
        super(source, paramEvent);
    }
}
