package pe.edu.certus.ratingsmodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.logic.ports.driver.ForRating;
import pe.edu.certus.ratingsmodule.logic.ports.mapper.ForMappingRating;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/ratings" )
public class RatingAdapter {

    private final ForRating forRating;
    private final ForMappingRating forMappingRating;

    public RatingAdapter( ForRating forRating, ForMappingRating forMappingRating ) {
        this.forRating = forRating;
        this.forMappingRating = forMappingRating;
    }

    @PostMapping( "/create" )
    public ResponseEntity< RatingWebModel > createRating( @Valid @RequestBody RatingWebModel ratingWebModel ) {
        try {
            RatingModel objectFromWeb = forMappingRating.fromWeb( ratingWebModel );
            forRating.createRating( objectFromWeb );
            return ResponseEntity.ok( ).build( );
        } catch ( IllegalArgumentException e ) {
            throw new RuntimeException( "FAILED TO CREATE RATING", e );
        }
    }

    @GetMapping( "/{id}" )
    public ResponseEntity< RatingWebModel > findRatingById( @PathVariable Long id ) {
        try {
            RatingModel ratingModel = ( RatingModel ) forRating.findRatingById( id );
            RatingWebModel response = forMappingRating.toWeb( ratingModel );
            return ResponseEntity.ok( response );
        } catch ( EntityNotFoundException e ) {
            return ResponseEntity.notFound( ).build( );
        } catch ( IllegalArgumentException e ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }

    @PutMapping( "/update" )
    public ResponseEntity< RatingWebModel > updateRating( @Valid @RequestBody RatingWebModel ratingWebModel ) {
        try {
            RatingModel objectFromWeb = forMappingRating.fromWeb( ratingWebModel );
            RatingModel updatedRating = ( RatingModel ) forRating.updateRating( objectFromWeb );
            RatingWebModel response = forMappingRating.toWeb( updatedRating );
            return ResponseEntity.ok( response );
        } catch ( EntityNotFoundException e ) {
            return ResponseEntity.notFound( ).build( );
        } catch ( IllegalArgumentException e ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity< Void > deleteRatingById( @PathVariable Long id ) {
        try {
            forRating.deleteRating( id );
            return ResponseEntity.ok( ).build( );
        } catch ( EntityNotFoundException e ) {
            return ResponseEntity.notFound( ).build( );
        } catch ( IllegalArgumentException e ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }

    @GetMapping
    public ResponseEntity< List< RatingWebModel > > findAllRatings( ) {
        try {
            List< RatingModel > ratingModels = forRating.findAllRatings( );
            List< RatingWebModel > response = ratingModels.stream( )
                    .map( forMappingRating::toWeb )
                    .collect( Collectors.toList( ) );
            return ResponseEntity.ok( response );
        } catch ( Exception e ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }
}
