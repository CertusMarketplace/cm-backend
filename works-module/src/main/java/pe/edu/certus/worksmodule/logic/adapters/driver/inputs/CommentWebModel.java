package pe.edu.certus.worksmodule.logic.adapters.driver.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommentWebModel(
        Long id,
        Long userId,
        String userName,
        String userAvatarUrl,
        String commentBody,
        LocalDateTime createdAt,
        List<CommentWebModel> replies
) {}