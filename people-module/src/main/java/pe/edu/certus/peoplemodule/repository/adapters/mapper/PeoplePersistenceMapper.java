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
                .personName(peopleEntity.getPersonName())
                .personLastname(peopleEntity.getPersonLastname())
                .personProfileImageUrl(peopleEntity.getPersonProfileImageUrl())
                .personDni(peopleEntity.getPersonDni())
                .personMobilePhone(peopleEntity.getPersonMobilePhone())
                .personGender(peopleEntity.getPersonGender())
                .personInstituteCampus(peopleEntity.getPersonInstituteCampus())
                .personInstitutionalEmail(peopleEntity.getPersonInstitutionalEmail())
                .personInstitutionalCareer(peopleEntity.getPersonInstitutionalCareer())
                .personCurrentTerm(peopleEntity.getPersonCurrentTerm())
                .build();
    }

    @Override
    public PeopleEntity toPersistence(PeopleModel peopleModel) {
        return PeopleEntity.builder()
                .personId(peopleModel.getPersonId())
                .idUser(peopleModel.getIdUser())
                .personName(peopleModel.getPersonName())
                .personLastname(peopleModel.getPersonLastname())
                .personProfileImageUrl(peopleModel.getPersonProfileImageUrl())
                .personDni(peopleModel.getPersonDni())
                .personMobilePhone(peopleModel.getPersonMobilePhone())
                .personGender(peopleModel.getPersonGender())
                .personInstituteCampus(peopleModel.getPersonInstituteCampus())
                .personInstitutionalEmail(peopleModel.getPersonInstitutionalEmail())
                .personInstitutionalCareer(peopleModel.getPersonInstitutionalCareer())
                .personCurrentTerm(peopleModel.getPersonCurrentTerm())
                .build();
    }
}