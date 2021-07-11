package nl.testautomation.demoapplication.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="loanrequests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @Id
    @GeneratedValue
    private int id;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private LoanType loanType;

    private int amount;
}
