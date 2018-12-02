package ru.vood.spring.lookUp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SchoolNotification {

    public double i = Math.random();
}
