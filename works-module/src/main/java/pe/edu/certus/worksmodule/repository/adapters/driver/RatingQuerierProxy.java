package pe.edu.certus.worksmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingRating;
import pe.edu.certus.worksmodule.repository.entity.RatingEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingRating;
import pe.edu.certus.worksmodule.repository.ports.driver.ForQueryingWork;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingRating;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingQuerierProxy implements ForManagingRating {

    private final ForQueryingRating forQueryingRating;
    private final ForQueryingWork forQueryingWork;
    private final ForBridgingRating forBridgingRating;

    public RatingQuerierProxy(ForQueryingRating forQueryingRating, ForQueryingWork forQueryingWork, ForBridgingRating forBridgingRating) {
        this.forQueryingRating = forQueryingRating;
        this.forQueryingWork = forQueryingWork;
        this.forBridgingRating = forBridgingRating;
    }

    @Override
    @Transactional
    public RatingModel saveRating(RatingModel ratingModel) {
        WorkEntity workEntity = forQueryingWork.findById(ratingModel.getWorkId())
                .orElseThrow(() -> new EntityNotFoundException("Work not found with ID: " + ratingModel.getWorkId()));

        var ratingEntity = forBridgingRating.toPersistence(ratingModel, workEntity);
        var savedEntity = forQueryingRating.save(ratingEntity);

        return forBridgingRating.fromPersistence(savedEntity);
    }

    @Override
    public List<RatingModel> satisfyFindAllRatings() {
        List<RatingEntity> entities = forQueryingRating.findAll();
        return entities.stream()
                .map(forBridgingRating::fromPersistence)
                .collect(Collectors.toList());
    }
}