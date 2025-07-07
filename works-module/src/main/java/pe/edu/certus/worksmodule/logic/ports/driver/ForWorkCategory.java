package pe.edu.certus.worksmodule.logic.ports.driver;

import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;

import java.util.List;

public interface ForWorkCategory {
    List<WorkCategoryModel> findAllCategories();
}