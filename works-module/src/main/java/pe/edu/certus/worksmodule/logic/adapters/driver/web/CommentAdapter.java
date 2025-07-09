package pe.edu.certus.worksmodule.logic.adapters.driver.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CommentWebModel;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CreateCommentWebModel;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForComment;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingComment;

@RestController
@RequestMapping("/api/v1")
public class CommentAdapter {

    private final ForComment forComment;
    private final ForMappingComment forMappingComment;

    public CommentAdapter( ForComment forComment, ForMappingComment forMappingComment) {
        this.forComment = forComment;
        this.forMappingComment = forMappingComment;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentWebModel> createComment(
            @Valid @RequestBody CreateCommentWebModel request,
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = Long.parseLong(authentication.getName());

        CommentModel domainModel = forMappingComment.fromRequest(request, userId);
        CommentModel createdComment = forComment.createComment(domainModel);

        return ResponseEntity.ok(forMappingComment.toWeb(createdComment));
    }
}