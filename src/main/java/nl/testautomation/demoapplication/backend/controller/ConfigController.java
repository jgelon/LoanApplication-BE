package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${endpoints.config}")
@CrossOrigin(origins = "*")
public class ConfigController {

    @GetMapping("/maritalstatus")
    public MaritalStatus[] maritalStatus() {
        return MaritalStatus.values();
    }

    @GetMapping("/genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @GetMapping("/incometypes")
    public IncomeType[] incometypes() {
        return IncomeType.values();
    }
}
