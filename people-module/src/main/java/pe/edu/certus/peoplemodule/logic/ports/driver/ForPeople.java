package pe.edu.certus.peoplemodule.logic.ports.driver;

import java.util.List;

public interface ForPeople<PeopleModel, Long> {
    void createPeople(PeopleModel peopleModel);
    PeopleModel findPeopleById(Long id);
    List<PeopleModel> findAllPeople();
    PeopleModel updatePeople(PeopleModel peopleModel);
    void deletePeople(Long id);
}