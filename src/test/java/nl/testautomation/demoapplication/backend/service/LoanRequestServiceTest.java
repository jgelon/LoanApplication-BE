package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.dto.ErrorDto;
import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.enums.Decision;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoanRequestServiceTest {

    @Mock
    private LoanRequestRepository loanRequestRepository;
    @Mock
    private LoanTypeService loanTypeService;

    @InjectMocks
    private LoanRequestService loanRequestService;

    @BeforeEach
    void initUseCase() {
        LoanType basicLoan = new LoanType(1, "Test Type", "Test Type", 500);
        when(loanTypeService.getLoanTypeById(1)).thenReturn(Optional.of(basicLoan));
        when(loanRequestRepository.save(any(LoanRequest.class))).then(returnsFirstArg());
    }

    @Test
    void addNewRequestValid() {
        var amount = 600;
        var request = new LoanRequestDto().setLoanTypeId(1).setAmount(amount);

        var response = loanRequestService.addNewRequest(request);
        var body = (LoanRequest)response.getBody();
        assertThat(body.getAmount(), is(amount));
    }

    @Test
    void addNewRequestInvalidTooLowAmount() {
        var amount = 200;
        var request = new LoanRequestDto().setLoanTypeId(1).setAmount(amount);

        var response = loanRequestService.addNewRequest(request);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        var responseBody = (ErrorDto)response.getBody();
        assertThat(responseBody.getErrorId(), is(601));
        assertThat(responseBody.getMessage(), containsString("The requested amount is to low for this type of loan"));
    }

    @Test
    void approveLoanRequest() {
        LoanRequest loan = new LoanRequest().setId(1).setFirstName("Test").setDecision(Decision.OPEN);
        when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loan));

        var response = loanRequestService.approveLoanRequest(1);
        assertThat(response.isPresent(), is(true));
        assertThat(response.get().getDecision(), is(Decision.APPROVED));
    }

    @Test
    void declineLoanRequest() {
        var loan = new LoanRequest().setId(1).setFirstName("Test").setDecision(Decision.OPEN);
        when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loan));

        var response = loanRequestService.declineLoanRequest(1);
        assertThat(response.isPresent(), is(true));
        assertThat(response.get().getDecision(), is(Decision.DECLINED));
    }
}