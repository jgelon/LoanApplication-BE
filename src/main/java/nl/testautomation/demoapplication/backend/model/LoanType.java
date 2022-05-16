package nl.testautomation.demoapplication.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="loantypes")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoanType {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private int minAmount;
}
