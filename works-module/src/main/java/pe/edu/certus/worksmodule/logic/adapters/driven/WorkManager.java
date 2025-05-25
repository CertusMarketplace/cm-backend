package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.util.List;

@Service
public class WorkManager implements ForWork< WorkModel, Long > {

    private final ForManagingWork forManagingWork;

    public WorkManager( ForManagingWork forManagingWork ) { this.forManagingWork = forManagingWork; }

    @Override
    public void createWork( WorkModel workModel ) {

        try {
            forManagingWork.satisfyCreateWork( workModel );
            System.out.println( "THE WORK HAS BEEN CREATED SUCCESSFULLY" );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "ERROR CREATING THE WORK: " + e.getMessage( ) );
            e.printStackTrace( );
        }

    }

    @Override
    public WorkModel findWorkById( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            WorkModel workModel = forManagingWork.satisfyFindWorkById( id );
            System.out.println( "THE WORK HAS BEEN FOUND SUCCESSFULLY" );

            if ( workModel == null ) {
                throw new IllegalArgumentException( "WORK NOT FOUND" );
            }

            return workModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND WORK MODEL", e );
        }
    }


    @Override
    public WorkModel updateWork( WorkModel workModel ) {
        try {
            if ( workModel == null ) {
                throw new IllegalArgumentException( "WORK MODEL CANNOT BE NULL" );
            }

            WorkModel updatedWorkModel = forManagingWork.satisfyUpdateWork( workModel );
            System.out.println( "WORK MODEL UPDATED SUCCESSFULLY" );

            return updatedWorkModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO UPDATE WORK MODEL", e );
        }
    }

    @Override
    public void deleteWork( Long id ) {

        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            forManagingWork.satisfyDeleteWorkById( id );
            System.out.println( "WORK MODEL DELETED SUCCESSFULLY" );

        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO DELETE WORK MODEL", e );
        }

    }

    @Override
    public List< WorkModel > findAllWorks( ) {
        try {
            List< WorkModel > workModels = forManagingWork.satisfyFindAllWork( );
            System.out.println( "ALL WORKS HAVE BEEN FOUND SUCCESSFULLY" );
            return workModels;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND ALL WORK MODELS", e );
        }
    }
}