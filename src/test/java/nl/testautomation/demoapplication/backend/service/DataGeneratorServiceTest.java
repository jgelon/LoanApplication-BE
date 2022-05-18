package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DataGeneratorServiceTest {

    @Mock
    private LoanTypeService loanTypeService;
    @Mock
    private LoanRequestRepository loanRequestRepository;
    @InjectMocks
    private DataGeneratorService dataGeneratorService;

    @BeforeEach
    void initUseCase() {
        LoanType basicLoan1 = new LoanType(1, "Test Type", "Test Type", 500);
        LoanType basicLoan2 = new LoanType(2, "Test Type", "Test Type", 50000);
        when(loanTypeService.getAllLoanTypes()).thenReturn(List.of(basicLoan1, basicLoan2));
        when(loanRequestRepository.save(any(LoanRequest.class))).then(returnsFirstArg());
    }

    @Test
    void generateLoanRequests() {
        List<LoanRequest> loanRequests = dataGeneratorService.generateLoanRequests(3);

        assertThat(loanRequests.size(), is(3));
        assertThat(loanRequests.get(0).getAmount(), is(greaterThan(loanRequests.get(0).getLoanType().getMinAmount())));
    }
}