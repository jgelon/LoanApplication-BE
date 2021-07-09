package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private ApplicationConfig applicationConfig;

    @GetMapping("/")
    public String index() {
        return "{'title'='Loan Application', endpoints='"+applicationConfig.getEndpoints().values()+"'}";
    }
}
