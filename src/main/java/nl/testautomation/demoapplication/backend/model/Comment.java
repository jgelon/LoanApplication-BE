package nl.testautomation.demoapplication.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

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
