package nl.testautomation.demoapplication.backend.service;

import com.github.javafaker.Faker;
import nl.testautomation.demoapplication.backend.enums.Decision;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DataGeneratorService {

    private static final SecureRandom random = new SecureRandom();
    private final Faker faker = new Faker(new Locale("nl"));

    @Autowired
    LoanRequestRepository loanRequestRepository;

    @Autowired
    LoanTypeService loanTypeService;


    public List<LoanRequest> generateLoanRequests(int amount) {
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
                    .setAmount(faker.number().numberBetween(loanType.getMinAmount(), loanType.getMinAmount() * 10))
                    .setDecision(Decision.OPEN);

            loanRequests.add(loanRequestRepository.save(loanRequest));
        }

        return loanRequests;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
