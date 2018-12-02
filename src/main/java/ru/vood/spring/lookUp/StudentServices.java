package ru.vood.spring.lookUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class StudentServices {

    @Autowired
    public SchoolNotification schoolNotification;

    @Lookup
    public SchoolNotification getNotification() {
        return null;
    }


}
