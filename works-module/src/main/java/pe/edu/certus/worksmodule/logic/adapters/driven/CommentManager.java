package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingComment;
import pe.edu.certus.worksmodule.logic.ports.driver.ForComment;

@Service
public class CommentManager implements ForComment {

    private final ForManagingComment forManagingComment;

    public CommentManager(ForManagingComment forManagingComment) {
        this.forManagingComment = forManagingComment;
    }

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        return forManagingComment.satisfyCreateComment(commentModel);
    }
}