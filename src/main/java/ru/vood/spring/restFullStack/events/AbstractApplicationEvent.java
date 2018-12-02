package ru.vood.spring.restFullStack.events;

import org.springframework.context.ApplicationEvent;


public abstract class AbstractApplicationEvent<S, M extends ParamEvent> extends ApplicationEvent {

    private M paramEvent;

    private AbstractApplicationEvent(S source) {
        super(source);
    }

    public AbstractApplicationEvent(S source, M paramEvent) {
        this(source);
        paramEvent.setSource(source);
        this.paramEvent = paramEvent;
    }

    public M getParamEvent() {
        return paramEvent;
    }
}
