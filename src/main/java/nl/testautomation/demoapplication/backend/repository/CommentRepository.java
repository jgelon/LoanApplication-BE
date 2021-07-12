package nl.testautomation.demoapplication.backend.repository;

import nl.testautomation.demoapplication.backend.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByRequestId(Integer requestId);
}
