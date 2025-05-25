package pe.edu.certus.peoplemodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driven.ForManagingPeople;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

import java.util.List;

@Service
public class PeopleManager implements ForPeople<PeopleModel, Long> {

    private final ForManagingPeople forManagingPeople;

    public PeopleManager(ForManagingPeople forManagingPeople) {
        this.forManagingPeople = forManagingPeople;
    }

    @Override
    public void createPeople(PeopleModel peopleModel) {
        try {
            forManagingPeople.satisfyCreatePeople(peopleModel);
            System.out.println("THE PERSON HAS BEEN CREATED SUCCESSFULLY");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR CREATING THE PERSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public PeopleModel findPeopleById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID CANNOT BE NULL");
            }

            PeopleModel peopleModel = forManagingPeople.satisfyFindPeopleById(id);
            System.out.println("THE PERSON HAS BEEN FOUND SUCCESSFULLY");

            if (peopleModel == null) {
                throw new IllegalArgumentException("PERSON NOT FOUND");
            }

            return peopleModel;
        } catch (Exception e) {
            throw new RuntimeException("FAILED TO FIND PERSON", e);
        }
    }

    @Override
    public PeopleModel updatePeople(PeopleModel peopleModel) {
        try {
            if (peopleModel == null) {
                throw new IllegalArgumentException("PERSON MODEL CANNOT BE NULL");
            }

            PeopleModel updatedPeopleModel = forManagingPeople.satisfyUpdatePeople(peopleModel);
            System.out.println("PERSON UPDATED SUCCESSFULLY");

            return updatedPeopleModel;
        } catch (Exception e) {
            throw new RuntimeException("FAILED TO UPDATE PERSON", e);
        }
    }

    @Override
    public void deletePeople(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID CANNOT BE NULL");
            }

            forManagingPeople.satisfyDeletePeopleById(id);
            System.out.println("PERSON DELETED SUCCESSFULLY");

        } catch (Exception e) {
            throw new RuntimeException("FAILED TO DELETE PERSON", e);
        }
    }

    @Override
    public List<PeopleModel> findAllPeople() {
        try {
            List<PeopleModel> peopleModels = forManagingPeople.satisfyFindAllPeople();
            System.out.println("ALL PEOPLE HAVE BEEN FOUND SUCCESSFULLY");
            return peopleModels;
        } catch (Exception e) {
            throw new RuntimeException("FAILED TO FIND ALL PEOPLE", e);
        }
    }
}