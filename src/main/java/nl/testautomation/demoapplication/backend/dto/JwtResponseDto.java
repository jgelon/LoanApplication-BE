package nl.testautomation.demoapplication.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class JwtResponseDto {
    @Getter
    private String jwttoken;
}
