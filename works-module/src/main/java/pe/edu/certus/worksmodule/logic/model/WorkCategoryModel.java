package pe.edu.certus.worksmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WorkCategoryModel {
    private Long id;
    private String name;
    private String description;
    private String bannerImageUrl;
}