package nl.testautomation.demoapplication.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class JwtResponseDto {
    @Getter
    private String jwttoken;
}
