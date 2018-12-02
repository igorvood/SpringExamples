package ru.vood.spring.rest;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
public class RESTControllerExample {

    protected static final Logger logger = Logger.getLogger(RESTControllerExample.class.getName());

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection<Employee> getEmployeeNames() {
        logger.info("currentThread -> " + Thread.currentThread().getName());
        return EmployeeSource.getEmployees();
    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    @GetMapping
    public Employee getEmployer(@RequestParam(value = "id", defaultValue = "01") String id) {

        return EmployeeSource.getEmployee(id);
    }

}
