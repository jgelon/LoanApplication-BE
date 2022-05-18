package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanReasonsServiceTest {
    @Mock
    private LoanReasonsRepository loanReasonsRepository;
    @InjectMocks
    private LoanReasonsService loanReasonsService;

    private LoanReason basicLoanreason;

    @BeforeEach
    void initUseCase() {
        basicLoanreason = new LoanReason(1, "Test", "Test");
    }

    @Test
    void getAllLoanReasons() {
        when(loanReasonsRepository.findAll()).thenReturn(List.of(basicLoanreason));

        List<LoanReason> allReasons = loanReasonsService.getAllReasons();
        assertThat(allReasons.size(), is(1));
        assertThat(allReasons.get(0).getTitle(), is(basicLoanreason.getTitle()));
    }
}