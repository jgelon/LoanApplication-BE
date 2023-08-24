package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.service.LoanTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@WebMvcTest(LoanTypeController.class)
class LoanTypeControllerTest extends ControllerTestBase {


    @MockBean
    private LoanTypeService loanTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllLoanTypes() throws Exception {
        var types = List.of(new LoanType());
        when(loanTypeService.getAllLoanTypes()).thenReturn(types);

        this.mockMvc.perform(get("/loantypes/"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(types)));
    }

    @Test
    void getLoan() throws Exception {
        var type = new LoanType();
        when(loanTypeService.getLoanTypeById(1)).thenReturn(Optional.of(type));

        this.mockMvc.perform(get("/loantypes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(type)));
    }

    @Test
    void getLoanNone() throws Exception {
        when(loanTypeService.getLoanTypeById(2)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/loantypes/2"))
                .andExpect(status().isNotFound());
    }
}