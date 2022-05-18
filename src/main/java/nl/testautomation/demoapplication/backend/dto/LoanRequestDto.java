package nl.testautomation.demoapplication.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;
import nl.testautomation.demoapplication.backend.model.LoanRequest;

import java.util.Date;

@NoArgsConstructor
@Data
public class LoanRequestDto {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String address;
    private String zipcode;
    private String city;
    private Date dob;
    private int income;
    private IncomeType incomeType;
    private MaritalStatus maritalStatus;
    private int loanTypeId;
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
                .setMaritalStatus(maritalStatus)
                .setAmount(amount);
    }
}
