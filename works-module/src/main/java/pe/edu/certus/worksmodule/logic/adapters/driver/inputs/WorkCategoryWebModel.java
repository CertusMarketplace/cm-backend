package pe.edu.certus.worksmodule.logic.adapters.driver.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkCategoryWebModel(
        Long id,
        String name,
        String description,
        String bannerImageUrl
) {}