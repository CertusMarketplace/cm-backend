package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingComment;
import pe.edu.certus.worksmodule.repository.entity.CommentEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingComment;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingComment;

@Service
public class CommentQuerierProxy implements ForManagingComment {

    private final ForQueryingComment forQueryingComment;
    private final ForQueryingWork forQueryingWork;
    private final ForBridgingComment forBridgingComment;

    public CommentQuerierProxy(ForQueryingComment forQueryingComment, ForQueryingWork forQueryingWork, ForBridgingComment forBridgingComment) {
        this.forQueryingComment = forQueryingComment;
        this.forQueryingWork = forQueryingWork;
        this.forBridgingComment = forBridgingComment;
    }

    @Override
    @Transactional
    public CommentModel satisfyCreateComment(CommentModel commentModel) {
        WorkEntity workEntity = forQueryingWork.findById(commentModel.getWorkId())
                .orElseThrow(() -> new EntityNotFoundException("Work not found with ID: " + commentModel.getWorkId()));

        CommentEntity parentEntity = null;
        if (commentModel.getParentCommentId() != null) {
            parentEntity = forQueryingComment.findById(commentModel.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent comment not found with ID: " + commentModel.getParentCommentId()));
        }

        CommentEntity commentEntity = forBridgingComment.toPersistence(commentModel, workEntity, parentEntity);
        CommentEntity savedEntity = forQueryingComment.save(commentEntity);

        return forBridgingComment.fromPersistence(savedEntity);
    }
}