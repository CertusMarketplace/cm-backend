package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingWork;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        forQueryingWork.save( objectFromDomain );
        System.out.println( "THE ENTITY HAS BEEN CREATED SUCCESSFULLY" );
    }


    @Override
    public WorkModel satisfyFindWorkById( Long id ) {
        return forQueryingWork.findById( id )
                .map( forBridgingWork::fromPersistence )
                .orElseThrow( ( ) -> new EntityNotFoundException(
                        "THE ENTITY WORK NOT FOUND WITH ID: " + id ) );
    }

    @Override
    public WorkModel satisfyUpdateWork( WorkModel workModel ) {

        WorkEntity objectFromDomain = forBridgingWork.toPersistence( workModel );
        objectFromDomain.setWorkPublishedAt( LocalDateTime.now( ) );
        objectFromDomain.setWorkUpdatedAt( LocalDateTime.now( ) );

        WorkEntity updatedEntity = forQueryingWork.save( objectFromDomain );
        System.out.println( "THE ENTITY HAS BEEN UPDATED SUCCESSFULLY" );

        return forBridgingWork.fromPersistence( updatedEntity );
    }

    @Override
    public void satisfyDeleteWorkById( Long id ) {

        Optional< WorkEntity > existingWork = forQueryingWork.findById( id );
        forQueryingWork.deleteById( id );
        System.out.println( "WORK ID: " + id + " IS DELETED" );

    }

    @Override
    public List<WorkModel> satisfyFindAllWork() {
        List<WorkEntity> workEntities = forQueryingWork.findAll();
        System.out.println("ALL ENTITIES HAVE BEEN FOUND SUCCESSFULLY");
        
        return workEntities.stream()
                .map(forBridgingWork::fromPersistence)
                .collect(Collectors.toList());
    }
}
