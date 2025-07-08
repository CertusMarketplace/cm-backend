package pe.edu.certus.peoplemodule.logic.ports.driver;

import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import java.util.List;

public interface ForPeople {

    PeopleModel createPeople(PeopleModel peopleModel);

    PeopleModel updatePeople(PeopleModel peopleModel);

    PeopleModel findPeopleById(Long personId);

    PeopleModel findPeopleByIdUser(Long idUser);
    List<PeopleModel> findAllPeople();

    void deletePeople(Long id);

    void requestSellerRole(PeopleModel peopleModel, Long userId);
}