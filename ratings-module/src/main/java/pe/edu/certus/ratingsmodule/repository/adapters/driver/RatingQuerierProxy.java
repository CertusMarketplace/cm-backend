package pe.edu.certus.ratingsmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.logic.ports.driven.ForManagingRating;
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;
import pe.edu.certus.ratingsmodule.repository.ports.driver.ForQueryingRating;
import pe.edu.certus.ratingsmodule.repository.ports.mapper.ForBridgingRating;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingQuerierProxy implements ForManagingRating {
    private final ForQueryingRating forQueryingRating;
    private final ForBridgingRating forBridgingRating;

    public RatingQuerierProxy(ForQueryingRating forQueryingRating, ForBridgingRating forBridgingRating) {
        this.forQueryingRating = forQueryingRating;
        this.forBridgingRating = forBridgingRating;
    }

    @Override
    public void satisfyCreateRating(RatingModel ratingModel) {
        RatingEntity objectFromDomain = forBridgingRating.toPersistence(ratingModel);
        objectFromDomain.setRatingCreatedAt(LocalDateTime.now());
        forQueryingRating.save(objectFromDomain);
        System.out.println("THE RATING ENTITY HAS BEEN CREATED SUCCESSFULLY");
    }

    @Override
    public RatingModel satisfyFindRatingById(Long id) {
        return forQueryingRating.findById(id)
                .map(forBridgingRating::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "THE ENTITY RATING NOT FOUND WITH ID: " + id));
    }

    @Override
    public RatingModel satisfyUpdateRating(RatingModel ratingModel) {
        RatingEntity objectFromDomain = forBridgingRating.toPersistence(ratingModel);
        
        Optional<RatingEntity> existingRating = forQueryingRating.findById(objectFromDomain.getRatingId());
        if (existingRating.isEmpty()) {
            throw new EntityNotFoundException("RATING NOT FOUND WITH ID: " + objectFromDomain.getRatingId());
        }
        
        RatingEntity updatedEntity = forQueryingRating.save(objectFromDomain);
        System.out.println("THE RATING ENTITY HAS BEEN UPDATED SUCCESSFULLY");

        return forBridgingRating.fromPersistence(updatedEntity);
    }

    @Override
    public void satisfyDeleteRatingById(Long id) {
        Optional<RatingEntity> existingRating = forQueryingRating.findById(id);
        if (existingRating.isEmpty()) {
            throw new EntityNotFoundException("RATING NOT FOUND WITH ID: " + id);
        }
        
        forQueryingRating.deleteById(id);
        System.out.println("RATING ID: " + id + " IS DELETED");
    }

    @Override
    public List<RatingModel> satisfyFindAllRating() {
        List<RatingEntity> ratingEntities = forQueryingRating.findAll();
        System.out.println("ALL RATING ENTITIES HAVE BEEN FOUND SUCCESSFULLY");
        
        return ratingEntities.stream()
                .map(forBridgingRating::fromPersistence)
                .collect(Collectors.toList());
    }
}
