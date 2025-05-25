package pe.edu.certus.peoplemodule.repository.ports.mapper;

import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

public interface ForBridgingPeople {
    PeopleModel fromPersistence(PeopleEntity peopleEntity);
    PeopleEntity toPersistence(PeopleModel peopleModel);
}
