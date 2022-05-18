package nl.testautomation.demoapplication.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.testautomation.demoapplication.backend.config.JwtAuthenticationEntryPoint;
import nl.testautomation.demoapplication.backend.config.JwtTokenUtil;
import nl.testautomation.demoapplication.backend.service.JwtUserDetailsService;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ControllerTestBase {
    @MockBean
    JwtUserDetailsService jwtUserDetailsService;
    @MockBean
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    public String asJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
