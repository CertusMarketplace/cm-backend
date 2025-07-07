package pe.edu.certus.worksmodule.repository.ports.mapper;

import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;

public interface ForBridgingWorkCategory {
    WorkCategoryEntity toPersistence(WorkCategoryModel model);
    WorkCategoryModel fromPersistence(WorkCategoryEntity entity);
}