package nl.testautomation.demoapplication.backend.dto;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Value
public class JwtResponseDto {
    String token;
    String type = "Bearer";
    Collection<String> authorities;
    String username;

    public JwtResponseDto(String jwtToken, UserDetails userDetails) {
        token = jwtToken;
        username = userDetails.getUsername();
        authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
