package pe.edu.certus.worksmodule.repository.ports.mapper;

import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.repository.entity.CommentEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;

public interface ForBridgingComment {
    CommentEntity toPersistence(CommentModel model, WorkEntity workEntity, CommentEntity parentComment);
    CommentModel fromPersistence(CommentEntity entity);
}