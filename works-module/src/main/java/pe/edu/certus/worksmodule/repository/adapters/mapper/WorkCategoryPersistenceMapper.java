package pe.edu.certus.worksmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWorkCategory;

@Component
public class WorkCategoryPersistenceMapper implements ForBridgingWorkCategory {

    @Override
    public WorkCategoryEntity toPersistence(WorkCategoryModel model) {
        WorkCategoryEntity entity = new WorkCategoryEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setBannerImageUrl(model.getBannerImageUrl());
        return entity;
    }

    @Override
    public WorkCategoryModel fromPersistence(WorkCategoryEntity entity) {
        return WorkCategoryModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .bannerImageUrl(entity.getBannerImageUrl())
                .build();
    }
}