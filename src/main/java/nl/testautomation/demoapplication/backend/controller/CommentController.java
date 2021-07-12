package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.model.Comment;
import nl.testautomation.demoapplication.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/${endpoints.comments}")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/request/{requestId}")
    public List<Comment> requestComments(@PathVariable Integer requestId) {
        return commentService.getComments(requestId);
    }

    @PostMapping("/request/{requestId}")
    public Comment addCommentToRequest(@PathVariable Integer requestId, @RequestBody String commentText) {
        return commentService.save(requestId, commentText);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer commentId) {
        return ResponseEntity.of(commentService.getSingleComment(commentId));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer commentId, @RequestBody String commentText) {
        Optional<Comment> update = commentService.update(commentId, commentText);
        if (update.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.of(update);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        commentService.delete(commentId);
    }
}