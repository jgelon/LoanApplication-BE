package nl.testautomation.demoapplication.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.testautomation.demoapplication.backend.enums.Decision;
import nl.testautomation.demoapplication.backend.enums.Gender;
import nl.testautomation.demoapplication.backend.enums.IncomeType;
import nl.testautomation.demoapplication.backend.enums.MaritalStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="loanrequests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private LoanType loanType;

    private int amount;

    private Decision decision;
}
