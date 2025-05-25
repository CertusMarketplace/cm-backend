package pe.edu.certus.ratingsmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.logic.ports.driven.ForManagingRating;
import pe.edu.certus.ratingsmodule.logic.ports.driver.ForRating;

import java.util.List;

@Service
public class RatingManager implements ForRating< RatingModel, Long > {

    private final ForManagingRating forManagingRating;

    public RatingManager( ForManagingRating forManagingRating ) {
        this.forManagingRating = forManagingRating;
    }

    @Override
    public void createRating( RatingModel ratingModel ) {
        try {
            forManagingRating.satisfyCreateRating( ratingModel );
            System.out.println( "THE RATING HAS BEEN CREATED SUCCESSFULLY" );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "ERROR CREATING THE RATING: " + e.getMessage( ) );
            e.printStackTrace( );
        }
    }

    @Override
    public RatingModel findRatingById( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            RatingModel ratingModel = forManagingRating.satisfyFindRatingById( id );
            System.out.println( "THE RATING HAS BEEN FOUND SUCCESSFULLY" );

            if ( ratingModel == null ) {
                throw new IllegalArgumentException( "RATING NOT FOUND" );
            }

            return ratingModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND RATING MODEL", e );
        }
    }


    @Override
    public RatingModel updateRating( RatingModel ratingModel ) {
        try {
            if ( ratingModel == null ) {
                throw new IllegalArgumentException( "RATING MODEL CANNOT BE NULL" );
            }

            RatingModel updatedRatingModel = forManagingRating.satisfyUpdateRating( ratingModel );
            System.out.println( "RATING MODEL UPDATED SUCCESSFULLY" );

            return updatedRatingModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO UPDATE RATING MODEL", e );
        }
    }

    @Override
    public void deleteRating( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            forManagingRating.satisfyDeleteRatingById( id );
            System.out.println( "RATING MODEL DELETED SUCCESSFULLY" );

        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO DELETE RATING MODEL", e );
        }
    }

    @Override
    public List< RatingModel > findAllRatings( ) {
        try {
            List< RatingModel > ratingModels = forManagingRating.satisfyFindAllRating( );
            System.out.println( "ALL RATINGS HAVE BEEN FOUND SUCCESSFULLY" );
            return ratingModels;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND ALL RATING MODELS", e );
        }
    }
}
