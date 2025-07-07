package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;

import java.util.List;

public interface ForManagingWorkCategory {
    List<WorkCategoryModel> satisfyFindAllCategories();
}