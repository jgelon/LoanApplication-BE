package nl.testautomation.demoapplication.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "Go to /swagger-ui/ for more information";
    }

}
