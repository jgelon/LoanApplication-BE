package nl.testautomation.demoapplication.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int errorId;
    private String message;
}
