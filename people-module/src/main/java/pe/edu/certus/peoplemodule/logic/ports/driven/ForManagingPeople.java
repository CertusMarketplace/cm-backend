package pe.edu.certus.peoplemodule.logic.ports.driven;

import pe.edu.certus.peoplemodule.logic.model.PeopleModel;

import java.util.List;

public interface ForManagingPeople {
    void satisfyCreatePeople(PeopleModel peopleModel);
    PeopleModel satisfyFindPeopleById(Long id);
    List<PeopleModel> satisfyFindAllPeople();
    PeopleModel satisfyUpdatePeople(PeopleModel peopleModel);
    void satisfyDeletePeopleById(Long id);
}