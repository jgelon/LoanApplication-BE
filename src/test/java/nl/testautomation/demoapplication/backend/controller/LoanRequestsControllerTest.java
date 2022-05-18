package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.dto.LoanRequestDto;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.service.DataGeneratorService;
import nl.testautomation.demoapplication.backend.service.LoanRequestService;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebMvcTest(LoanRequestsController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoanRequestsControllerTest extends ControllerTestBase {

    @MockBean
    private LoanRequestService loanRequestService;
    @MockBean
    private DataGeneratorService dataGeneratorService;
    @MockBean
    private LoanTypeService loanTypeService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private LoanRequest baseRequest;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        baseRequest = new LoanRequest()
                .setFirstName("FirstName")
                .setAmount(5000);
        when(loanRequestService.getAllLoanRequests()).thenReturn(List.of(baseRequest));
        when(loanRequestService.getLoanRequest(any())).thenReturn(Optional.of(baseRequest));
    }

    @Test
    void addNewRequest() throws Exception {
       LoanRequestDto dto = new LoanRequestDto()
                .setFirstName("FirstName")
                .setAmount(5000)
                .setGender(Gender.FEMALE)
                .setDob(Date.from(Instant.now()))
                .setLoanTypeId(1);

       when(loanRequestService.addNewRequest(dto)).thenReturn(ResponseEntity.ok().body(dto.toLoanRequest()));

       this.mockMvc.perform(post("/loanrequests/new")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(asJson(dto)))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_READ" })
    void getAllLoanRequests() throws Exception {
        this.mockMvc.perform(get("/loanrequests/admin/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void generate() throws Exception {
        this.mockMvc.perform(get("/loanrequests/generate/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void getAllLoanRequestsNoAuth() throws Exception {
        this.mockMvc.perform(get("/loanrequests/admin/")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_READ" })
    void getLoanRequests() throws Exception {
        this.mockMvc.perform(get("/loanrequests/admin/id/1")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void getLoanRequestsNoAuth() throws Exception {
        this.mockMvc.perform(get("/loanrequests/admin/id/1")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_DELETE" })
    void deleteLoanRequests() throws Exception {
        this.mockMvc.perform(delete("/loanrequests/admin/id/1")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void deleteLoanRequestsNoAuth() throws Exception {
        this.mockMvc.perform(delete("/loanrequests/admin/id/1")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_WRITE" })
    void approve() throws Exception {
        when(loanRequestService.approveLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/approve/1")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void approveNoAuth() throws Exception {
        when(loanRequestService.approveLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/approve/1")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_WRITE" })
    void decline() throws Exception {
        when(loanRequestService.declineLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/decline/1")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void declineNoAuth() throws Exception {
        when(loanRequestService.declineLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/decline/1")).andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_DELETE_ALL" })
    void deleteAll() throws Exception {
        when(loanRequestService.declineLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/clear")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = { "REQUEST_DELETE" })
    void deleteAllNoAuth() throws Exception {
        when(loanRequestService.declineLoanRequest(any())).thenReturn(Optional.of(baseRequest));
        this.mockMvc.perform(post("/loanrequests/admin/clear")).andExpect(status().isForbidden());
    }
}