package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanTypeServiceTest {
    @Mock
    private LoanTypeRepository loanTypeRepository;
    @InjectMocks
    private LoanTypeService loanTypeService;

    private LoanType basicLoan;

    @BeforeEach
    void initUseCase() {
        basicLoan = new LoanType(1, "Test", "Test", 500);
    }

    @Test
    void getAllLoanTypes() {
        when(loanTypeRepository.findAll()).thenReturn(List.of(basicLoan));

        List<LoanType> allLoanTypes = loanTypeService.getAllLoanTypes();
        assertThat(allLoanTypes.size(), is(1));
        assertThat(allLoanTypes.get(0).getMinAmount(), is(basicLoan.getMinAmount()));
    }

    @Test
    void getLoanTypeById() {
        when(loanTypeRepository.findById(any())).thenReturn(Optional.of(basicLoan));

        Optional<LoanType> loan = loanTypeService.getLoanTypeById(1);
        assertThat(loan.isPresent(), is(true));
    }
}