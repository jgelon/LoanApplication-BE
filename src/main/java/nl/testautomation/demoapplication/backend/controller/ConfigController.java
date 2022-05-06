package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;
import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.repository.PrivilegeRepository;
import nl.testautomation.demoapplication.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${endpoints.config}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConfigController {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/permissions")
    public Iterable<Privilege> permissions() {
        return privilegeRepository.findAll();
    }

    @GetMapping("/users")
    public Iterable<User> users() {
        return userRepository.findAll();
    }
}
