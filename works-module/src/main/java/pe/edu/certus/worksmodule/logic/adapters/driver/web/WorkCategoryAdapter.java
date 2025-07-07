package pe.edu.certus.worksmodule.logic.adapters.driver.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkCategoryWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWorkCategory;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWorkCategory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/work-categories")
public class WorkCategoryAdapter {

    private final ForWorkCategory forWorkCategory;
    private final ForMappingWorkCategory forMappingWorkCategory;

    public WorkCategoryAdapter(ForWorkCategory forWorkCategory, ForMappingWorkCategory forMappingWorkCategory) {
        this.forWorkCategory = forWorkCategory;
        this.forMappingWorkCategory = forMappingWorkCategory;
    }

    @GetMapping()
    public ResponseEntity<List<WorkCategoryWebModel>> getAllCategories() {
        List<WorkCategoryModel> categories = forWorkCategory.findAllCategories();
        List< WorkCategoryWebModel > response = categories.stream()
                .map(forMappingWorkCategory::toWeb)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}