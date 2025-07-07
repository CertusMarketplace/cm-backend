package pe.edu.certus.worksmodule.logic.adapters.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkCategoryWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWorkCategory;

public class WorkCategoryMapper implements ForMappingWorkCategory {

    @Override
    public WorkCategoryModel fromWeb(WorkCategoryWebModel webModel) {
        return WorkCategoryModel.builder()
                .id(webModel.id())
                .name(webModel.name())
                .description(webModel.description())
                .bannerImageUrl(webModel.bannerImageUrl())
                .build();
    }

    @Override
    public WorkCategoryWebModel toWeb(WorkCategoryModel model) {
        return new WorkCategoryWebModel(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getBannerImageUrl()
        );
    }
}