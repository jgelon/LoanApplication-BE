package nl.testautomation.demoapplication.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
@Data
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private int id;
    private String text;
    private int requestId;
}
