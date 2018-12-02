package ru.vood.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RunRest implements CommandLineRunner {

    protected static final Logger logger = Logger.getLogger(RunRest.class.getName());

    @Autowired
    private RESTClientExample clientExample;

    @Override
    public void run(String... args) {
        //System.out.println(this.getClass() + " -> " + clientExample.getAllEmployees());
        logger.info(/*this.getClass() + */" -> " + clientExample.getAllEmployees());
    }
}
