package nl.testautomation.demoapplication.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="lenders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lender {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

    private int amount;
}
