package pe.edu.certus.peoplemodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
import pe.edu.certus.peoplemodule.logic.ports.mapper.ForMappingPeople;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleAdapter {

    private final ForPeople forPeople;
    private final ForMappingPeople forMappingPeople;

    public PeopleAdapter(ForPeople forPeople, ForMappingPeople forMappingPeople) {
        this.forPeople = forPeople;
        this.forMappingPeople = forMappingPeople;
    }

    // ENDPOINT SEGURO AÑADIDO PARA OBTENER PERFIL DEL USUARIO LOGUEADO
    @GetMapping("/me")
    public ResponseEntity<PeopleWebModel> getCurrentPersonProfile(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Long userId = Long.parseLong(authentication.getName());
            PeopleModel peopleModel = (PeopleModel) forPeople.findPeopleById(userId);
            return ResponseEntity.ok(forMappingPeople.toWeb(peopleModel));
        } catch (NumberFormatException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPeople(@Valid @RequestBody PeopleWebModel peopleWebModel) {
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

    @PutMapping("/update/{userId}")
    public ResponseEntity<PeopleWebModel> updatePeople(@PathVariable Long userId, @Valid @RequestBody PeopleWebModel peopleWebModel) {
        try {
            // Aseguramos que el idUser del path se use
            PeopleModel peopleToUpdate = forMappingPeople.fromWeb(peopleWebModel);
            peopleToUpdate.setIdUser(userId);

            PeopleModel updatedPeople = (PeopleModel) forPeople.updatePeople(peopleToUpdate);
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