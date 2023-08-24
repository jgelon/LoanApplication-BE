package nl.testautomation.demoapplication.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@WebMvcTest(BaseController.class)
class BaseControllerTest extends ControllerTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isIAmATeapot())
                .andExpect(content().string(containsString("Go to /swagger-ui/ for more information")));
    }

    @Test
    void secure() throws Exception {
        this.mockMvc.perform(post("/secure").header("Authorization", "Bearer Fake")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Requested a secure endpoint")));
    }

    @Test
    void insecure() throws Exception {
        this.mockMvc.perform(post("/secure")).andDo(print()).andExpect(status().isForbidden())
                .andExpect(content().string(containsString("Authorization header required")));
    }
}