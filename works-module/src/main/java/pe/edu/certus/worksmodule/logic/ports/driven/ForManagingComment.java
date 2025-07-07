package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.CommentModel;

public interface ForManagingComment {
    CommentModel satisfyCreateComment(CommentModel commentModel);
}