package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.util.Collections;
import java.util.List;

@Service
public class WorkManager implements ForWork< WorkModel, Long > {

    private final ForManagingWork forManagingWork;

    public WorkManager( ForManagingWork forManagingWork ) { this.forManagingWork = forManagingWork; }

    @Override
    public void createWork( WorkModel workModel ) {

        try {
            forManagingWork.satisfyCreateWork( workModel );
            System.out.println( "THE METHOD CREATED IS IN PROCESS OF SATISFACTION" );

        } catch ( IllegalArgumentException e ) {
            System.out.println( "ERROR: " + e.getMessage( ) );
            e.printStackTrace( );
        }
    }

    @Override
    public WorkModel findWorkModelById( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID cannot be null" );
            }
            System.out.println( "THE METHOD OF FINDING THE OPERATOR IS IN PROCESS OF SATISFACTION" );
            WorkModel workModel = forManagingWork.satisfyFindWorkById( id );
            if ( workModel == null ) {
                throw new RuntimeException( "Work not found" );
            }
            return workModel;
        } catch ( Exception e ) {
            System.out.println( "ERROR: " + e.getMessage( ) );
            return null;
        }
    }
//
//    @Override
//    public List< WorkModel > findAllWorkModel( ) {
//        try{
//            System.out.println( "THE METHOD OF FINDING ALL OPERATORS IS IN PROCESS OF SATISFACTION" );
//            List< WorkModel > workModels = forManagingWork.satisfyFindAllWork( );
//            return workModels;
//        } catch ( Exception e ) {
//            System.out.println( "ERROR RETRIEVING ALL WORK MODELS: " + e.getMessage( ) );
//            return Collections.emptyList();
//        }
//    }


    @Override
    public WorkModel updateWorkModel( WorkModel workModel ) {
        try{
            if (workModel == null){
                throw new IllegalArgumentException("WORK MODEL CANNOT BE NULL");
            }
            System.out.println("UPDATING WORK MODEL..." );
            WorkModel updatedWorkModel = forManagingWork.satisfyUpdateWork(workModel);
            System.out.println("WORK MODEL UPDATED SUCCESSFULLY..." );
            return updatedWorkModel;
        } catch (Exception e){
            System.out.println("ERROR UPDATING WORK MODEL: " + e.getMessage() );
            throw new RuntimeException( "FAILED TO UPDATE WORK MODEL", e );
        }
    }

    @Override
    public void deleteWorkModel( Long id ) {
        try{
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            System.out.println("DELETING WORK MODEL WITH ID: "+ id);
            forManagingWork.satisfyDeleteWorkById( id );
            System.out.println("WORK MODEL DELETED SUCCESSFULLY..." );
        } catch (Exception e) {
            System.out.println("ERROR DELETING WORK MODEL: " + e.getMessage() );
            throw new RuntimeException("FAILED TO DELETE WORK MODEL", e);
        }
    }
}