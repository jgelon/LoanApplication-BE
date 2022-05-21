package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.repository.LoanReasonsRepository;
import nl.testautomation.demoapplication.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUsername("test")).thenReturn(new User().setUsername("test"));
        var user = jwtUserDetailsService.loadUserByUsername("test");
        assertThat(user.getUsername(), is("test"));
    }
    @Test
    void loadUserByUsernameFailed() {
        when(userRepository.findByUsername("test")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername("test"));
    }

}