package pe.edu.certus.peoplemodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
import pe.edu.certus.peoplemodule.logic.ports.mapper.ForMappingPeople;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleAdapter {

    private final ForPeople forPeople;
    private final ForMappingPeople forMappingPeople;

    public PeopleAdapter(ForPeople forPeople, ForMappingPeople forMappingPeople) {
        this.forPeople = forPeople;
        this.forMappingPeople = forMappingPeople;
    }

    @PostMapping("/create")
    public ResponseEntity<PeopleWebModel> createPeople(@Valid @RequestBody PeopleWebModel peopleWebModel) {
        try {
            PeopleModel objectFromWeb = forMappingPeople.fromWeb(peopleWebModel);
            forPeople.createPeople(objectFromWeb);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ERROR CREATING PERSON", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeopleWebModel> findPeopleById(@PathVariable Long id) {
        try {
            PeopleModel peopleModel = (PeopleModel) forPeople.findPeopleById(id);
            PeopleWebModel response = forMappingPeople.toWeb(peopleModel);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PeopleWebModel> updatePeople(@Valid @RequestBody PeopleWebModel peopleWebModel) {
        try {
            PeopleModel objectFromWeb = forMappingPeople.fromWeb(peopleWebModel);
            PeopleModel updatedPeople = (PeopleModel) forPeople.updatePeople(objectFromWeb);
            PeopleWebModel response = forMappingPeople.toWeb(updatedPeople);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeopleById(@PathVariable Long id) {
        try {
            forPeople.deletePeople(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PeopleWebModel>> findAllPeople() {
        try {
            List<PeopleModel> peopleModels = forPeople.findAllPeople();
            List<PeopleWebModel> response = peopleModels.stream()
                    .map(forMappingPeople::toWeb)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("ERROR FINDING ALL PEOPLE", e);
        }
    }
}