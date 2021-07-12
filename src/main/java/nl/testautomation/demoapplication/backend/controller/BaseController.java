package nl.testautomation.demoapplication.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity
            .status(HttpStatus.I_AM_A_TEAPOT)
            .body("Go to /swagger-ui/ for more information");
    }

}
