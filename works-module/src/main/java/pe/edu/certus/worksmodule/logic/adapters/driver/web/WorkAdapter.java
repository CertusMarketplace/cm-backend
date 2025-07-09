package pe.edu.certus.worksmodule.logic.adapters.driver.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.WorkWebModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/works")
public class WorkAdapter {

    private final ForWork forWork;
    private final ForMappingWork forMappingWork;

    public WorkAdapter(ForWork forWork, ForMappingWork forMappingWork) {
        this.forWork = forWork;
        this.forMappingWork = forMappingWork;
    }

    @PostMapping
    public ResponseEntity<WorkWebModel> createWork(@Valid @RequestBody WorkWebModel workWebModel) {
        WorkModel domainModel = forMappingWork.fromWeb(workWebModel);
        WorkModel createdWork = ( WorkModel ) forWork.createWork(domainModel);
        return ResponseEntity.ok(forMappingWork.toWeb(createdWork));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkWebModel> findWorkById(@PathVariable Long id) {
        try {
            WorkModel workModel = ( WorkModel ) forWork.findWorkById(id);
            return ResponseEntity.ok(forMappingWork.toWeb(workModel));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity< WorkWebModel > updateWork( @PathVariable Long id, @Valid @RequestBody WorkWebModel workWebModel) {
        try {
            WorkModel domainModel = forMappingWork.fromWeb(workWebModel);
            domainModel.setWorkId(id);
            WorkModel updatedWork = ( WorkModel ) forWork.updateWork(domainModel);
            return ResponseEntity.ok(forMappingWork.toWeb(updatedWork));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkById(@PathVariable Long id) {
        try {
            forWork.deleteWork(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<WorkWebModel>> findAllWorks(
            @RequestParam(required = false, defaultValue = "todos") String category,
            @RequestParam(required = false, defaultValue = "all") String priceRange,
            @RequestParam(required = false) Integer popularity) {

        List<WorkModel> workModelList = forWork.findAllWorks(category, priceRange, popularity);

        List<WorkWebModel> workWebModelList = workModelList.stream()
                .map(forMappingWork::toWeb)
                .collect( Collectors.toList());

        return ResponseEntity.ok(workWebModelList);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<WorkWebModel>> findWorksBySellerId(@PathVariable Long sellerId) {
        try {
            List<WorkModel> workModels = forWork.findAllBySellerId(sellerId);
            List<WorkWebModel> response = workModels.stream()
                    .map(forMappingWork::toWeb)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/by-ids")
    public ResponseEntity<List<WorkWebModel>> findWorksByIds(@RequestParam List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<WorkModel> workModels = forWork.findWorksByIds(ids);
        List<WorkWebModel> response = workModels.stream()
                .map(forMappingWork::toWeb)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}