package pe.edu.certus.worksmodule.logic.ports.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkCategoryWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;

public interface ForMappingWorkCategory {
    WorkCategoryModel fromWeb(WorkCategoryWebModel webModel);
    WorkCategoryWebModel toWeb(WorkCategoryModel model);
}