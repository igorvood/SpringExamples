package ru.vood.spring.lookUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RunLookup implements CommandLineRunner {

    protected static final Logger logger = Logger.getLogger(RunLookup.class.getName());

    @Autowired
    private StudentServices services;

/*
    @PostConstruct
    public void run() {
        System.out.println("Lookupq 1 -> " + services.getNotification());
        System.out.println("Lookupq 2 -> " + services.getNotification());
        System.out.println("Lookupq 3 -> " + services.getNotification());
    }
*/

    @Override
    public void run(String... args) {
        logger.info("Lookup 1 -> " + services.getNotification().i);
        logger.info("Lookup 2 -> " + services.getNotification().i);

    }
}
