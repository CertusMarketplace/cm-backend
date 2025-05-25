package pe.edu.certus.peoplemodule.logic.ports.mapper;

import pe.edu.certus.peoplemodule.logic.adapters.driver.PeopleWebModel;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;

public interface ForMappingPeople {
    PeopleModel fromWeb(PeopleWebModel peopleWebModel);
    PeopleWebModel toWeb(PeopleModel peopleModel);
}