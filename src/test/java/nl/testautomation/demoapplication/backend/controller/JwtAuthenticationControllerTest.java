package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.config.MyUserPrincipal;
import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;
import nl.testautomation.demoapplication.backend.dto.JwtRequestDto;
import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@ContextConfiguration
@WebMvcTest(JwtAuthenticationController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JwtAuthenticationControllerTest extends ControllerTestBase {

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        when(jwtTokenUtil.generateToken(any())).thenReturn("mytoken");
        when(jwtUserDetailsService.loadUserByUsername(any())).thenReturn(new MyUserPrincipal(new User().setUsername("test").setPrivileges(Set.of(new Privilege("priv"),new Privilege("priv2")))));
    }

    @Test
    void login() throws Exception {
        var dto = new JwtRequestDto().setUsername("test").setPassword("test");
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mytoken"))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.authorities").value(hasItems("priv", "priv2")));
    }
}