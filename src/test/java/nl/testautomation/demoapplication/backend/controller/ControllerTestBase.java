package nl.testautomation.demoapplication.backend.controller;

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
}
