package ru.vood.spring.restFullStack.rowMappers.mappedObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String login;
    private String fio;
    private String email;
    private String position;
    private String department;

}
