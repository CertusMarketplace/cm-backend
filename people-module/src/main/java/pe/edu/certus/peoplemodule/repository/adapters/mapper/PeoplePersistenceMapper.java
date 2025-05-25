package pe.edu.certus.peoplemodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;
import pe.edu.certus.peoplemodule.repository.ports.mapper.ForBridgingPeople;

@Component
public class PeoplePersistenceMapper implements ForBridgingPeople {

    @Override
    public PeopleModel fromPersistence(PeopleEntity peopleEntity) {
        return PeopleModel.builder()
                .personId(peopleEntity.getPersonId())
                .idUser(peopleEntity.getIdUser())
                .idCareer(peopleEntity.getIdCareer())
                .personName(peopleEntity.getPersonName())
                .personLastname(peopleEntity.getPersonLastname())
                .personDni(peopleEntity.getPersonDni())
                .personMobilePhone(peopleEntity.getPersonMobilePhone())
                .personGender(peopleEntity.getPersonGender())
                .personInstituteLocation(peopleEntity.getPersonInstituteLocation())
                .personInstitutionalEmail(peopleEntity.getPersonInstitutionalEmail())
                .personInstitutionalCycle(peopleEntity.getPersonInstitutionalCycle())
                .build();
    }

    @Override
    public PeopleEntity toPersistence(PeopleModel peopleModel) {
        return PeopleEntity.builder()
                .personId(peopleModel.getPersonId())
                .idUser(peopleModel.getIdUser())
                .idCareer(peopleModel.getIdCareer())
                .personName(peopleModel.getPersonName())
                .personLastname(peopleModel.getPersonLastname())
                .personDni(peopleModel.getPersonDni())
                .personMobilePhone(peopleModel.getPersonMobilePhone())
                .personGender(peopleModel.getPersonGender())
                .personInstituteLocation(peopleModel.getPersonInstituteLocation())
                .personInstitutionalEmail(peopleModel.getPersonInstitutionalEmail())
                .personInstitutionalCycle(peopleModel.getPersonInstitutionalCycle())
                .build();
    }
}