package nl.testautomation.demoapplication.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name="loanreasons")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoanReason {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
}
