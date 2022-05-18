package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.LoanRequestRepository;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanRequestsController.class)
class LoanRequestsControllerTest extends ControllerTestBase {

//    @MockBean
//    private LoanRequestService loanRequestService;

    @MockBean
    private LoanRequestRepository loanRequestRepository;
    @MockBean
    private LoanTypeService loanTypeService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void addNewRequest() throws Exception {
       LoanRequestDto dto = new LoanRequestDto()
                .setAddress("Address")
                .setAmount(5000)
                .setGender(Gender.FEMALE)
                .setDob(Date.from(Instant.now()))
                .setLoanTypeId(1);
       when(loanTypeService.getLoanTypeById(any())).thenReturn(Optional.of(new LoanType(1, "Test", "Description", 500)));

//       when(loanRequestService.addNewRequest(dto)).thenReturn(ResponseEntity.ok().body(dto.toLoanRequest()));

       this.mockMvc.perform(post("/loanrequest/new", dto))
               .andDo(print()).andExpect(status().isOk());
    }
}