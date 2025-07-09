package pe.edu.certus.worksmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set; // Importa Set

@Getter
@Setter
@Builder
public class CommentModel {
    private Long id;
    private Long userId;
    private Long workId;
    private Long parentCommentId;
    private String commentBody;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CommentModel> replies; // CAMBIADO de List a Set
}