package ru.vood.spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RESTControllerExample {
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection<Employee> getEmployeeNames() {
        return EmployeeSource.getEmployees();
    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public Employee getEmployer(@RequestParam(value = "id", defaultValue = "01") String id) {
        return EmployeeSource.getEmployee(id);
    }

}
