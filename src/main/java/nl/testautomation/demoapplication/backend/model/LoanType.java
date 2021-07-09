package nl.testautomation.demoapplication.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loantypes")
@Data
@RequiredArgsConstructor
public class LoanType {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int minAmount;
}
