package nl.testautomation.demoapplication.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.testautomation.demoapplication.backend.model.Loan;

@NoArgsConstructor
@Getter
public class LenderDto {
    private int id;
    private String firstName;
    private String lastName;

    private Loan loan;

    private int amount;
}
