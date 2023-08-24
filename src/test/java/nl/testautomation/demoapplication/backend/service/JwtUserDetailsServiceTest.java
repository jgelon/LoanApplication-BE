package nl.testautomation.demoapplication.backend.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nl.testautomation.demoapplication.backend.model.User;
import nl.testautomation.demoapplication.backend.repository.UserRepository;

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