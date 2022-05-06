package nl.testautomation.demoapplication.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="privileges")
@Data
@NoArgsConstructor
public class Privilege implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Privilege(String name) {
        this.name = name;
    }
}
