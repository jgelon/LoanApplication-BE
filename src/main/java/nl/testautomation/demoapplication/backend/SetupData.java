package nl.testautomation.demoapplication.backend;

import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
import nl.testautomation.demoapplication.backend.repository.PrivilegeRepository;
import nl.testautomation.demoapplication.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SetupData {
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanReasonsRepository loanReasonsRepository;

    private Iterable<Privilege> privileges;

    @Autowired
    public SetupData(
            UserRepository userRepository,
            PrivilegeRepository privilegeRepository,
            BCryptPasswordEncoder passwordEncoder,
            LoanTypeRepository loanTypeRepository,
            LoanReasonsRepository loanReasonsRepository
    ) {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.loanTypeRepository = loanTypeRepository;
        this.loanReasonsRepository = loanReasonsRepository;
    }

    @PostConstruct
    public void init() {
        initPrivileges();
        initUsers();
        generateDefaultLoanTypes();
        generateDefaultLoanReasons();
    }

    private void initPrivileges() {
        HashSet<Privilege> privilegesList = new HashSet<>(Arrays.asList(
                new Privilege("COMMENT_READ"),
                new Privilege("COMMENT_WRITE"),
                new Privilege("COMMENT_DELETE"),
                new Privilege("REQUEST_READ"),
                new Privilege("REQUEST_WRITE"),
                new Privilege("REQUEST_DELETE"),
                new Privilege("REQUEST_DELETE_ALL")
        ));

        privileges = privilegeRepository.saveAll(privilegesList);
    }

    @java.lang.SuppressWarnings("java:S6437")
    private void initUsers() {
        Set<Privilege> allPriv = new HashSet<>();
        privileges.forEach(allPriv::add);

        User user0 = new User()
                .setUsername("superadmin")
                .setPassword(passwordEncoder.encode("superadmin"))
                .setPrivileges(allPriv);
        userRepository.save(user0);

        Set<Privilege> adminPriv = allPriv.stream().filter(p -> !p.getName().contains("_ALL")).collect(Collectors.toSet());
        User user1 = new User()
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("admin"))
                .setPrivileges(adminPriv);
        userRepository.save(user1);

        Set<Privilege> readPriv = allPriv.stream().filter(p -> p.getName().contains("READ")).collect(Collectors.toSet());
        User user2 = new User()
                .setUsername("reader")
                .setPassword(passwordEncoder.encode("reader"))
                .setPrivileges(readPriv);
        userRepository.save(user2);
    }

    private void generateDefaultLoanTypes() {
        loanTypeRepository.save(new LoanType()
                .setTitle("Mini-loan")
                .setMinAmount(300));
        loanTypeRepository.save(new LoanType()
                .setTitle("Personal loan")
                .setMinAmount(1500));
        loanTypeRepository.save(new LoanType()
                .setTitle("Revolving credit")
                .setMinAmount(2500));
        loanTypeRepository.save(new LoanType()
                .setTitle("Car-loan")
                .setMinAmount(2000));
        loanTypeRepository.save(new LoanType()
                .setTitle("Mortgage")
                .setMinAmount(50000));
    }

    public void generateDefaultLoanReasons() {
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("Groceries")
                        .setDescription("It is not a good idea to apply for a loan for just groceries.")
        );
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("Investments")
                        .setDescription("Perhaps it is not such a good idea to apply for a loan for just investments.")
        );
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("Bills")
                        .setDescription("A Revolving Credit or a Personal loan is probably the way to go.")
        );
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("Furniture")
                        .setDescription("A mini-loan or a Personal loan is probably the best choice.")
        );
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("Car")
                        .setDescription("A Car-loan is the best choice when buying a car.")
        );
        loanReasonsRepository.save(
                new LoanReason()
                        .setTitle("House")
                        .setDescription("You need a mortgage.")
        );
    }
}