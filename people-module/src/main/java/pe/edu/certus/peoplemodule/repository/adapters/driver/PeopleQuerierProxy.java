package pe.edu.certus.peoplemodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driven.ForManagingPeople;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;
import pe.edu.certus.peoplemodule.repository.ports.driver.ForQueryingPeople;
import pe.edu.certus.peoplemodule.repository.ports.mapper.ForBridgingPeople;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleQuerierProxy implements ForManagingPeople {
    private final ForQueryingPeople forQueryingPeople;
    private final ForBridgingPeople forBridgingPeople;

    public PeopleQuerierProxy(ForQueryingPeople forQueryingPeople, ForBridgingPeople forBridgingPeople) {
        this.forQueryingPeople = forQueryingPeople;
        this.forBridgingPeople = forBridgingPeople;
    }

    @Override
    public void satisfyCreatePeople(PeopleModel peopleModel) {
        PeopleEntity objectFromDomain = forBridgingPeople.toPersistence(peopleModel);
        forQueryingPeople.save(objectFromDomain);
        System.out.println("PERSON ENTITY HAS BEEN CREATED SUCCESSFULLY");
    }

    @Override
    public PeopleModel satisfyFindPeopleById(Long id) {
        return forQueryingPeople.findById(id)
                .map(forBridgingPeople::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PERSON ENTITY NOT FOUND WITH ID: " + id));
    }

    @Override
    public PeopleModel satisfyUpdatePeople(PeopleModel peopleModel) {
        PeopleEntity objectFromDomain = forBridgingPeople.toPersistence(peopleModel);
        
        Optional<PeopleEntity> existingPeople = forQueryingPeople.findById(objectFromDomain.getPersonId());
        if (existingPeople.isEmpty()) {
            throw new EntityNotFoundException("PERSON NOT FOUND WITH ID: " + objectFromDomain.getPersonId());
        }
        
        PeopleEntity updatedEntity = forQueryingPeople.save(objectFromDomain);
        System.out.println("PERSON ENTITY HAS BEEN UPDATED SUCCESSFULLY");

        return forBridgingPeople.fromPersistence(updatedEntity);
    }

    @Override
    public void satisfyDeletePeopleById(Long id) {
        Optional<PeopleEntity> existingPeople = forQueryingPeople.findById(id);
        if (existingPeople.isEmpty()) {
            throw new EntityNotFoundException("PERSON NOT FOUND WITH ID: " + id);
        }
        
        forQueryingPeople.deleteById(id);
        System.out.println("PERSON ID: " + id + " HAS BEEN DELETED");
    }

    @Override
    public List<PeopleModel> satisfyFindAllPeople() {
        List<PeopleEntity> peopleEntities = forQueryingPeople.findAll();
        System.out.println("ALL PERSON ENTITIES HAVE BEEN FOUND SUCCESSFULLY");
        
        return peopleEntities.stream()
                .map(forBridgingPeople::fromPersistence)
                .collect(Collectors.toList());
    }
}