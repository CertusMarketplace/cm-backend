package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWorkCategory;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWorkCategory;

import java.util.List;

@Service
public class WorkCategoryManager implements ForWorkCategory {

    private final ForManagingWorkCategory forManagingWorkCategory;

    public WorkCategoryManager( ForManagingWorkCategory forManagingWorkCategory ) { this.forManagingWorkCategory = forManagingWorkCategory; }

    @Override
    public List<WorkCategoryModel> findAllCategories() {
        return forManagingWorkCategory.satisfyFindAllCategories();
    }
}