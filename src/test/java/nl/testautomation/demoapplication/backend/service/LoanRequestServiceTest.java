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
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.List;
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
        when(loanTypeService.getLoanTypeById(99)).thenReturn(Optional.empty());
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
    void addNewRequestInvalidLoantype() {
        var amount = 200;
        var request = new LoanRequestDto().setLoanTypeId(99).setAmount(amount);

        var response = loanRequestService.addNewRequest(request);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        var responseBody = (ErrorDto)response.getBody();
        assertThat(responseBody.getErrorId(), is(602));
        assertThat(responseBody.getMessage(), containsString("The provided loantype is not recognized"));
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
    void approveNonExistingLoanRequest() {
        when(loanRequestRepository.findById(1)).thenReturn(Optional.empty());

        var response = loanRequestService.approveLoanRequest(1);
        assertThat(response.isPresent(), is(false));
    }

    @Test
    void declineNonExistingLoanRequest() {
        when(loanRequestRepository.findById(1)).thenReturn(Optional.empty());

        var response = loanRequestService.declineLoanRequest(1);
        assertThat(response.isPresent(), is(false));
    }

    @Test
    void declineLoanRequest() {
        var loan = new LoanRequest().setId(1).setFirstName("Test").setDecision(Decision.OPEN);
        when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loan));

        var response = loanRequestService.declineLoanRequest(1);
        assertThat(response.isPresent(), is(true));
        assertThat(response.get().getDecision(), is(Decision.DECLINED));
    }

    @Test
    void getAll() {
        var amount = 600;
        var request = new LoanRequest().setAmount(amount);
        var request2 = new LoanRequest().setAmount(2*amount);
        when(loanRequestRepository.findAll()).thenReturn(List.of(new LoanRequest[]{request,request2}));;

        var response = loanRequestService.getAllLoanRequests();
        assertThat(response.size(), is(2));
        assertThat(response.get(1).getAmount(), is(2*amount));
    }
}