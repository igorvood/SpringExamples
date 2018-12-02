package ru.vood.spring.lookUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class StudentServices {

    public final SchoolNotification schoolNotification;

    @Autowired
    public StudentServices(SchoolNotification schoolNotification) {
        this.schoolNotification = schoolNotification;
    }

    @Lookup
    public SchoolNotification getNotification() {
        return null;
    }


}
