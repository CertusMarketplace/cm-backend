package pe.edu.certus.worksmodule.logic.adapters.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CommentWebModel;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.CreateCommentWebModel;
import pe.edu.certus.worksmodule.logic.model.CommentModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingComment;

import java.util.Collections;
import java.util.stream.Collectors;

public class CommentMapper implements ForMappingComment {

    @Override
    public CommentModel fromRequest( CreateCommentWebModel request) {
        return CommentModel.builder()
                .workId(request.workId())
                .userId(request.userId())
                .parentCommentId(request.parentCommentId())
                .commentBody(request.commentBody())
                .build();
    }

    @Override
    public CommentWebModel toWeb( CommentModel model) {
        if (model == null) return null;
        return new CommentWebModel(
                model.getId(),
                model.getUserId(),
                model.getCommentBody(),
                model.getCreatedAt(),
                model.getReplies() != null ? model.getReplies().stream().map(this::toWeb).collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}