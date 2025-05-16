package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WorkQuerierProxy implements ForManagingWork {
    private final ForQueryingWork forQueryingWork;
    private final ForBridgingWork forBridgingWork;

    public WorkQuerierProxy( ForQueryingWork forQueryingWork, ForBridgingWork forBridgingWork ) {
        this.forQueryingWork = forQueryingWork;
        this.forBridgingWork = forBridgingWork;
    }

    @Override
    public void satisfyCreateWork( WorkModel workModel ) {
        WorkEntity objectFromDomain = forBridgingWork.toPersistence( workModel );
        System.out.println( "THE METHOD OF SATISFYING THE CREATION IS SENDING THE DATA TO THE ENTITY" );
        forQueryingWork.save( objectFromDomain );
        System.out.println( "THE DATA OF THE ENTITY HAS BEEN SAVED IN THE DATABASE" );
    }

//    @Override
//    public List< WorkModel > satisfyFindAllWork( ) {
//        List< WorkEntity > workEntities = forQueryingWork.findAll( );
//        return forBridgingWork.toPersistence( workEntities )
//    }

    @Override
    public WorkModel satisfyFindWorkById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        return forQueryingWork.findById(id)
                .map(forBridgingWork::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Trabajo no encontrado con ID: " + id));
    }

    @Override
    public WorkModel satisfyUpdateWork(WorkModel workModel) {
        if (workModel == null || workModel.getWorkId() == null) {
            throw new IllegalArgumentException("WorkModel y workId no pueden ser null");
        }

        try {
            Optional<WorkEntity> existingWork = forQueryingWork.findById(workModel.getWorkId().longValue());
            if (existingWork.isEmpty()) {
                throw new EntityNotFoundException("Trabajo no encontrado con ID: " + workModel.getWorkId());
            }

            WorkEntity objectFromDomain = forBridgingWork.toPersistence(workModel);
            objectFromDomain.setWorkPublishedAt( LocalDateTime.now() );
            objectFromDomain.setWorkUpdatedAt( LocalDateTime.now());

            WorkEntity updatedEntity = forQueryingWork.save(objectFromDomain);
            return forBridgingWork.fromPersistence(updatedEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el trabajo", e);
        }
    }

    @Override
    public void satisfyDeleteWorkById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        try {
            Optional<WorkEntity> existingWork = forQueryingWork.findById(id);
            if (existingWork.isEmpty()) {
                throw new EntityNotFoundException("Trabajo no encontrado con ID: " + id);
            }

            forQueryingWork.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el trabajo", e);
        }
    }
}
