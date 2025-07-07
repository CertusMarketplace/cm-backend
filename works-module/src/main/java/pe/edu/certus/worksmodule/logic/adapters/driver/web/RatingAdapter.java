package pe.edu.certus.worksmodule.logic.adapters.driver.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.RatingWebModel;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForRating;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingRating;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingAdapter {

    private final ForRating forRating;
    private final ForMappingRating forMappingRating;

    public RatingAdapter(ForRating forRating, ForMappingRating forMappingRating) {
        this.forRating = forRating;
        this.forMappingRating = forMappingRating;
    }

    @PostMapping
    public ResponseEntity< RatingWebModel > createRating( @Valid @RequestBody RatingWebModel ratingWebModel) {
        RatingModel domainModel = forMappingRating.fromWeb(ratingWebModel);
        RatingModel createdRating = forRating.createRating(domainModel);
        return ResponseEntity.ok(forMappingRating.toWeb(createdRating));
    }

    @GetMapping
    public ResponseEntity<List<RatingWebModel>> getAllRatings() {
        List<RatingModel> ratings = forRating.findAllRatings();
        List<RatingWebModel> response = ratings.stream()
                .map(forMappingRating::toWeb)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}