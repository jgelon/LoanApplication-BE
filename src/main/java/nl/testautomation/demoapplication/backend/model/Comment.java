package nl.testautomation.demoapplication.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="comments")
@Data
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private int requestId;
}
