package nl.testautomation.demoapplication.backend.config;

import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.service.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JwtRequestFilterTest {

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    private JwtRequestFilter filter;

    private String token;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil("secret");
        var user = new User()
                .setId(1L)
                .setUsername("test")
                .setPassword("test")
                .setPrivileges(Set.of(new Privilege("priv"),new Privilege("priv2")));
        var userDetails = new MyUserPrincipal(user);
        token = jwtTokenUtil.generateToken(userDetails);

        when(jwtUserDetailsService.loadUserByUsername("test")).thenReturn(userDetails);
    }

    @Test
    void validateValidToken() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilterInternal(req, res, chain);

        assertEquals(200, res.getStatus());
    }

    @Test
    void validateNoToken() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilterInternal(req, res, chain);

        assertEquals(200, res.getStatus());
    }

    @Test
    void validateNullToken() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("Authorization", "Bearer null");
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilterInternal(req, res, chain);

        assertEquals(200, res.getStatus());
    }

}