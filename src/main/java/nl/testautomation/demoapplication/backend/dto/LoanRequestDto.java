package nl.testautomation.demoapplication.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;

@NoArgsConstructor
@Getter
public class LoanRequestDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String zipcode;
    private String city;

    private LoanType loanType;

    private int amount;

    public LoanRequest toLoanRequest() {
        return new LoanRequest()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setZipcode(zipcode)
                .setCity(city)
                .setLoanType(loanType)
                .setAmount(amount);
    }
}
