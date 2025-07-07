package pe.edu.certus.worksmodule.repository.adapters.driver;


import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWorkCategory;
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWorkCategory;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWorkCategory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkCategoryQuerierProxy implements ForManagingWorkCategory {

    private final ForQueryingWorkCategory forQueryingWorkCategory;
    private final ForBridgingWorkCategory forBridgingWorkCategory;

    public WorkCategoryQuerierProxy( ForQueryingWorkCategory forQueryingWorkCategory, ForBridgingWorkCategory forBridgingWorkCategory ) {
        this.forQueryingWorkCategory = forQueryingWorkCategory;
        this.forBridgingWorkCategory = forBridgingWorkCategory;
    }

    @Override
    public List< WorkCategoryModel > satisfyFindAllCategories( ) {
        List< WorkCategoryEntity > entities = forQueryingWorkCategory.findAll();
        return entities.stream()
                .map(forBridgingWorkCategory::fromPersistence)
                .collect( Collectors.toList());
    }
}
