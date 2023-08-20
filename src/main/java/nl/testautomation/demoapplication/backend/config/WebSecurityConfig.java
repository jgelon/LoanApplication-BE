package nl.testautomation.demoapplication.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import nl.testautomation.demoapplication.backend.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(s -> s.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/comments/**", "/loanrequests/admin/**").authenticated()
                        .anyRequest().permitAll())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(r -> r.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(r -> r.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Add a filter to validate the tokens with every request
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                ;
        return httpSecurity.build();
    }
}
