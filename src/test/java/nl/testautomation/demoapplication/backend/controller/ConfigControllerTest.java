package nl.testautomation.demoapplication.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;
import nl.testautomation.demoapplication.backend.repository.PrivilegeRepository;
import nl.testautomation.demoapplication.backend.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@WebMvcTest(ConfigController.class)
class ConfigControllerTest extends ControllerTestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PrivilegeRepository privilegeRepository;
    @MockBean
    UserRepository userRepository;

    @Test
    void maritalstatus() throws Exception {
        this.mockMvc.perform(get("/config/maritalstatus"))
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void genders() throws Exception {
        this.mockMvc.perform(get("/config/genders"))
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void incometypes() throws Exception {
        this.mockMvc.perform(get("/config/incometypes"))
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void permissions() throws Exception {
        this.mockMvc.perform(get("/config/permissions"))
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void users() throws Exception {
        this.mockMvc.perform(get("/config/users"))
            .andDo(print()).andExpect(status().isOk());
    }
}