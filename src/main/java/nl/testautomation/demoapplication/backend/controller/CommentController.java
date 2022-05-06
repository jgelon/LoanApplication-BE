package nl.testautomation.demoapplication.backend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import nl.testautomation.demoapplication.backend.model.Comment;
import nl.testautomation.demoapplication.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/${endpoints.comments}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/request/{requestId}")
    @PreAuthorize("hasAuthority('COMMENT_READ')")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access Token", required = true, example = "Bearer access_token")
    public List<Comment> requestComments(@PathVariable Integer requestId) {
        return commentService.getComments(requestId);
    }

    @PostMapping("/request/{requestId}")
    @PreAuthorize("hasAuthority('COMMENT_WRITE')")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access Token", required = true, example = "Bearer access_token")
    public Comment addCommentToRequest(@PathVariable Integer requestId, @RequestBody String commentText) {
        return commentService.save(requestId, commentText);
    }

    @GetMapping("/comment/{commentId}")
    @PreAuthorize("hasAuthority('COMMENT_READ')")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access Token", required = true, example = "Bearer access_token")
    public ResponseEntity<Comment> getComment(@PathVariable Integer commentId) {
        return ResponseEntity.of(commentService.getSingleComment(commentId));
    }

    @PutMapping("/comment/{commentId}")
    @PreAuthorize("hasAuthority('COMMENT_WRITE')")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access Token", required = true, example = "Bearer access_token")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer commentId, @RequestBody String commentText) {
        Optional<Comment> update = commentService.update(commentId, commentText);
        if (update.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.of(update);
    }

    @DeleteMapping("/comment/{commentId}")
    @PreAuthorize("hasAuthority('COMMENT_DELETE')")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access Token", required = true, example = "Bearer access_token")
    public void deleteComment(@PathVariable Integer commentId) {
        commentService.delete(commentId);
    }
}