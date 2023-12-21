package nl.testautomation.demoapplication.backend.controller;

import nl.testautomation.demoapplication.backend.config.WebSecurityConfig;
import nl.testautomation.demoapplication.backend.model.Comment;
import nl.testautomation.demoapplication.backend.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({WebSecurityConfig.class})
@ContextConfiguration
@WebMvcTest(CommentController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentControllerTest extends ControllerTestBase {

    @MockBean
    private CommentService commentService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private Comment baseComment;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        baseComment = new Comment()
            .setId(1)
            .setRequestId(10)
            .setText("Test Text");
        when(commentService.getSingleComment(1)).thenReturn(Optional.of(baseComment));
        when(commentService.getComments(10)).thenReturn(List.of(baseComment));
        when(commentService.save(anyInt(), anyString())).thenReturn(baseComment);
        doNothing().when(commentService).delete(anyInt());
    }

    @Test
    @WithMockUser(authorities = { "COMMENT_READ" })
    void requestComments() throws Exception {
       this.mockMvc.perform(get("/comments/request/10"))
               .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void requestCommentsNoAuth() throws Exception {
       this.mockMvc.perform(get("/comments/request/10"))
               .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "COMMENT_READ" })
    void comment() throws Exception {
       this.mockMvc.perform(get("/comments/comment/1"))
               .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void commentNoAuth() throws Exception {
       this.mockMvc.perform(get("/comments/comment/1"))
               .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "COMMENT_WRITE" })
    void postComment() throws Exception {
       this.mockMvc.perform(post("/comments/request/10"))
               .andExpect(status().isOk());
    }
    @Test
    @WithAnonymousUser
    void postCommentNoAuth() throws Exception {
       this.mockMvc.perform(post("/comments/request/10"))
               .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "COMMENT_WRITE" })
    void updateComment() throws Exception {
       this.mockMvc.perform(put("/comments/comment/1"))
               .andExpect(status().isOk());
    }
    @Test
    @WithAnonymousUser
    void updateCommentNoAuth() throws Exception {
       this.mockMvc.perform(put("/comments/request/10"))
               .andExpect(unauthenticated());
    }

    @Test
    @WithMockUser(authorities = { "COMMENT_DELETE" })
    void deleteComment() throws Exception {
        this.mockMvc.perform(delete("/comments/comment/1")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void deleteCommentNoAuth() throws Exception {
        this.mockMvc.perform(delete("/comments/comment/1")).andExpect(unauthenticated());
    }
}