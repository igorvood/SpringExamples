package ru.vood.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("restClient")
public class RESTClientExample {

    private final RestTemplate restTemplate;

    @Autowired
    public RESTClientExample(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllEmployees() {
        return restTemplate.getForObject("http://localhost:8070/employees", String.class);
    }
}
