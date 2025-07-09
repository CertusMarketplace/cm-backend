package pe.edu.certus.worksmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.repository.entity.CommentEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingComment;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CommentPersistenceMapper implements ForBridgingComment {

    @Override
    public CommentEntity toPersistence(CommentModel model, WorkEntity workEntity, CommentEntity parentComment) {
        return CommentEntity.builder()
                .id(model.getId())
                .work(workEntity)
                .userId(model.getUserId())
                .parentComment(parentComment)
                .commentBody(model.getCommentBody())
                .isDeleted(model.getIsDeleted() != null ? model.getIsDeleted() : false)
                .build();
    }

    @Override
    public CommentModel fromPersistence(CommentEntity entity) {
        if (entity == null) return null;
        return CommentModel.builder()
                .id(entity.getId())
                .workId(entity.getWork() != null ? entity.getWork().getWorkId() : null)
                .userId(entity.getUserId())
                .parentCommentId(entity.getParentComment() != null ? entity.getParentComment().getId() : null)
                .commentBody(entity.getCommentBody())
                .isDeleted(entity.getIsDeleted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .replies(entity.getReplies() != null ? entity.getReplies().stream()
                        .map(this::fromPersistence)
                        .collect(Collectors.toSet()) : Collections.emptySet()) // CORREGIDO
                .build();
    }
}