package pe.edu.certus.worksmodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

@RestController
@RequestMapping( "/api/v1/works" )
public class WorkAdapter {

    private final ForWork forWork;
    private final ForMappingWork forMappingWork;

    public WorkAdapter( ForWork forWork, ForMappingWork forMappingWork ) {
        this.forWork = forWork;
        this.forMappingWork = forMappingWork;
    }

    @RequestMapping( "/create" )
    public ResponseEntity< WorkWebModel > createWork( @Valid @RequestBody WorkWebModel workWebModel ) {

        try {
            WorkModel objectFromWeb = forMappingWork.fromWeb( workWebModel );
            forWork.createWork( objectFromWeb );
            return ResponseEntity.ok( ).build( );

        } catch ( IllegalArgumentException e ) {
            System.out.println( "ERROR: " + e.getMessage( ) );
            return ResponseEntity.badRequest( ).build( );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkWebModel> findWorkById(@PathVariable Long id) {
        try {
            WorkModel workModel = ( WorkModel ) forWork.findWorkModelById(id);
            WorkWebModel response = forMappingWork.toWeb(workModel);
            return ResponseEntity.ok(response);
        } catch ( EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<WorkWebModel> updateWork(@Valid @RequestBody WorkWebModel workWebModel) {
        try {
            WorkModel objectFromWeb = forMappingWork.fromWeb(workWebModel);
            WorkModel updatedWork = ( WorkModel ) forWork.updateWorkModel(objectFromWeb);
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
            forWork.deleteWorkModel(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
