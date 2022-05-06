package nl.testautomation.demoapplication.backend;

import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.repository.PrivilegeRepository;
import nl.testautomation.demoapplication.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SetupData {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Iterable<Privilege> privileges;

    @PostConstruct
    public void init() {
        initPrivileges();
        initUsers();
    }

    private void initPrivileges() {
        HashSet<Privilege> privilegesList = new HashSet<>(Arrays.asList(
                new Privilege("COMMENT_READ"),
                new Privilege("COMMENT_WRITE"),
                new Privilege("COMMENT_DELETE"),
                new Privilege("REQUEST_READ"),
                new Privilege("REQUEST_WRITE"),
                new Privilege("REQUEST_DELETE")
        ));

        privileges = privilegeRepository.saveAll(privilegesList);
    }

    private void initUsers() {
        Set<Privilege> allPriv = new HashSet<>();
        privileges.forEach(allPriv::add);

        User user1 = new User()
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("admin"))
                .setPrivileges(allPriv);
        userRepository.save(user1);


        Set<Privilege> readPriv = allPriv.stream().filter(p -> p.getName().contains("READ")).collect(Collectors.toSet());
        User user2 = new User()
                .setUsername("reader")
                .setPassword(passwordEncoder.encode("reader"))
                .setPrivileges(readPriv);
        userRepository.save(user2);
    }
}