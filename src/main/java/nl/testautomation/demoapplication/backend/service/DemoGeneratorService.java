package nl.testautomation.demoapplication.backend.service;

import com.github.javafaker.Faker;
import java.security.SecureRandom;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;
import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoGeneratorService {

    private static final SecureRandom random = new SecureRandom();

    @Autowired
    LoanTypeRepository loanTypeRepository;
    @Autowired
    LoanReasonsRepository loanReasonsRepository;
    @Autowired
    LoanRequestRepository loanRequestRepository;

    @Autowired
    LoanTypeService loanTypeService;

    public void generateDefaultLoanTypes() {
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

    public List<LoanRequest> generateLoanRequests(int amount) {
        Faker faker = new Faker();
        List<LoanType> allLoanTypes = loanTypeService.getAllLoanTypes();
        List<LoanRequest> loanRequests = new ArrayList<>();
        for(int i = 0; i<amount; i++) {
            LoanType loanType = allLoanTypes.get(random.nextInt(allLoanTypes.size()));

            LoanRequest loanRequest = new LoanRequest()
                    .setGender(randomEnum(Gender.class))
                    .setFirstName(faker.name().firstName())
                    .setLastName(faker.name().lastName())
                    .setAddress(faker.address().streetAddress())
                    .setZipcode(faker.address().zipCode())
                    .setCity(faker.address().city())
                    .setDob(faker.date().birthday(18, 85))
                    .setIncome(faker.number().numberBetween(25000, 250000))
                    .setIncomeType(randomEnum(IncomeType.class))
                    .setMaritalStatus(randomEnum(MaritalStatus.class))
                    .setLoanType(loanType)
                    .setAmount(faker.number().numberBetween(loanType.getMinAmount(), loanType.getMinAmount() * 10));

            loanRequests.add(loanRequestRepository.save(loanRequest));
        }

        return loanRequests;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
