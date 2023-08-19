package nl.testautomation.demoapplication.backend.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.Set;

import org.exparity.hamcrest.date.DateMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.MalformedJwtException;
import nl.testautomation.demoapplication.backend.model.Privilege;
import nl.testautomation.demoapplication.backend.model.User;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    private String token;
    private User user;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil("secret");
        user = new User()
                .setId(1L)
                .setUsername("test")
                .setPassword("test")
                .setPrivileges(Set.of(new Privilege("priv"),new Privilege("priv2")));
        var userDetails = new MyUserPrincipal(user);
        token = jwtTokenUtil.generateToken(userDetails);
    }

    @Test
    void nullToken() {
        assertThrows(MalformedJwtException.class, () -> jwtTokenUtil.getUsernameFromToken("null"));
    }

    @Test
    void getUsernameFromToken() {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        assertThat(username, is(user.getUsername()));
    }

    @Test
    void getExpirationDateFromToken() {
        var expirationdate  = jwtTokenUtil.getExpirationDateFromToken(token);
        assertThat(expirationdate, DateMatchers.after(new Date()));
    }

    @Test
    void validateToken() {
        var validuser = jwtTokenUtil.validateToken(token, new MyUserPrincipal(user));
        assertThat(validuser, is(true));
    }

    @Test
    void validateTokenInvalid() {
        var validuser = jwtTokenUtil.validateToken(token, new MyUserPrincipal(new User()));
        assertThat(validuser, is(false));
    }
}