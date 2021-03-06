package ru.vood.spring.restFullStack.events.example;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.vood.spring.restFullStack.events.AbstractApplicationListener;
import ru.vood.spring.restFullStack.events.ParamEvent;

@Component
public class ExampleApplicationListener implements AbstractApplicationListener<ParamEvent, ExampleApplicationEvent> {

    private ExampleApplicationEvent exampleApplicationEvent;

    @Override
    public void onApplicationEvent(@NotNull ExampleApplicationEvent event) {
        this.exampleApplicationEvent = event;
    }

    public ExampleApplicationEvent getExampleApplicationEvent() {
        return exampleApplicationEvent;
    }
}
