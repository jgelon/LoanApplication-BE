package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;
import nl.testautomation.demoapplication.backend.model.LoanReason;
import nl.testautomation.demoapplication.backend.service.LoanReasonsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@WebMvcTest(LoanReasonController.class)
class LoanReasonControllerTest extends ControllerTestBase {

    @MockBean
    private LoanReasonsService loanReasonsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllLoanReasons() throws Exception {
        var reasons = List.of(new LoanReason());
        when(loanReasonsService.getAllReasons()).thenReturn(reasons);

        this.mockMvc.perform(get("/loanreasons/"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(reasons)));
    }
}