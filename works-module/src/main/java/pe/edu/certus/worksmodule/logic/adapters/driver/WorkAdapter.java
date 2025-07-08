package pe.edu.certus.worksmodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork; // Import correcto
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/works")
public class WorkAdapter {

    private final ForWork forWork;
    private final ForMappingWork forMappingWork;

    // Constructor corregido
    public WorkAdapter(ForWork forWork, ForMappingWork forMappingWork) {
        this.forWork = forWork;
        this.forMappingWork = forMappingWork;
    }

    @PostMapping("/create")
    public ResponseEntity<WorkWebModel> createWork(@Valid @RequestBody WorkWebModel workWebModel) {
        try {
            WorkModel objectFromWeb = forMappingWork.fromWeb(workWebModel);
            forWork.createWork(objectFromWeb);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("FAILED TO CREATE WORK", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkWebModel> findWorkById(@PathVariable Long id) {
        try {
            WorkModel workModel = (WorkModel) forWork.findWorkById(id);
            WorkWebModel response = forMappingWork.toWeb(workModel);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<WorkWebModel> updateWork(@Valid @RequestBody WorkWebModel workWebModel) {
        try {
            WorkModel objectFromWeb = forMappingWork.fromWeb(workWebModel);
            WorkModel updatedWork = (WorkModel) forWork.updateWork(objectFromWeb);
            WorkWebModel response = forMappingWork.toWeb(updatedWork);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkById(@PathVariable Long id) {
        try {
            forWork.deleteWork(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WorkWebModel>> findAllWorks(
            @RequestParam(required = false, defaultValue = "todos") String category,
            @RequestParam(required = false, defaultValue = "all") String priceRange,
            @RequestParam(required = false) Integer popularity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<WorkModel> workPage = forWork.findAllWorks(category, priceRange, popularity, pageable);
            List<WorkWebModel> response = workPage.getContent().stream()
                    .map(forMappingWork::toWeb)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<WorkWebModel>> findWorksBySeller(@PathVariable Long sellerId) {
        List<WorkModel> sellerWorksModels = forWork.findWorksBySellerId(sellerId);
        List<WorkWebModel> response = sellerWorksModels.stream()
                .map(forMappingWork::toWeb)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}