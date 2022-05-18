package nl.testautomation.demoapplication.backend.service;

import nl.testautomation.demoapplication.backend.model.Comment;
import nl.testautomation.demoapplication.backend.model.LoanRequest;
import nl.testautomation.demoapplication.backend.model.LoanType;
import nl.testautomation.demoapplication.backend.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void initUseCase() {
        Comment comment1 = new Comment().setText("Test").setRequestId(1).setId(1);
        Comment comment2 = new Comment().setText("Test").setRequestId(1).setId(2);
        when(commentRepository.findById(1)).thenReturn(Optional.of(comment1));
        when(commentRepository.findByRequestId(1)).thenReturn(List.of(comment1, comment2));
        when(commentRepository.save(any(Comment.class))).then(returnsFirstArg());
    }

    @Test
    void getComments() {
        var comments = commentService.getComments(1);
        assertThat(comments.size(), is(2));
    }

    @Test
    void save() {
        Comment response = commentService.save(2, "Test2");
        assertThat(response.getRequestId(), is(2));
        assertThat(response.getText(), is("Test2"));
    }

    @Test
    void getSingleComment() {
        Optional<Comment> response = commentService.getSingleComment(1);
        assertThat(response.isPresent(), is(true));
        assertThat(response.get().getRequestId(), is(1));
    }

    @Test
    void update() {
        Optional<Comment>  response = commentService.update(1, "New text");
        assertThat(response.isPresent(), is(true));
        assertThat(response.get().getText(), is("New text"));

    }
}