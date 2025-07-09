package pe.edu.certus.worksmodule.logic.ports.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CommentWebModel;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CreateCommentWebModel;
import pe.edu.certus.worksmodule.logic.model.CommentModel;

public interface ForMappingComment {
    CommentModel fromRequest(CreateCommentWebModel request, Long userId);
    CommentWebModel toWeb( CommentModel model);
}