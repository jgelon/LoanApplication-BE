package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.Comment;
import nl.testautomation.demoapplication.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getComments(int requestId){
        return commentRepository.findByRequestId(requestId);
    }

    public Comment save(int requestId, String text) {
        return commentRepository.save(new Comment().setRequestId(requestId).setText(text));
    }

    public Optional<Comment> getSingleComment(Integer commentId) {
        return commentRepository.findById(commentId);
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> update(int commentId, String commentText) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);
        return existingComment.map(comment -> commentRepository.save(comment.setText(commentText)));
    }
}
