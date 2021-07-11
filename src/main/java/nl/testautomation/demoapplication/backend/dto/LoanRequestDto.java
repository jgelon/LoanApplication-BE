package nl.testautomation.demoapplication.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;

import java.util.Date;

@NoArgsConstructor
@Data
public class LoanRequestDto {
    private String gender;
    private String firstName;
    private String lastName;
    private String address;
    private String zipcode;
    private String city;
    private Date dob;
    private int income;
    private String incomeType;
    private String maritialStatus;

    private LoanType loanType;

    private int amount;

    public LoanRequest toLoanRequest() {
        return new LoanRequest()
                .setGender(gender)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setZipcode(zipcode)
                .setCity(city)
                .setDob(dob)
                .setIncome(income)
                .setIncomeType(incomeType)
                .setMaritialStatus(maritialStatus)
                .setLoanType(loanType)
                .setAmount(amount);
    }
}
