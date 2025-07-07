package pe.edu.certus.peoplemodule.logic.adapters.mapper;

import pe.edu.certus.peoplemodule.logic.adapters.driver.PeopleWebModel;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.mapper.ForMappingPeople;

public class PeopleMapper implements ForMappingPeople {

    @Override
    public PeopleModel fromWeb(PeopleWebModel peopleWebModel) {
        return PeopleModel.builder()
                .personId(peopleWebModel.personId())
                .idUser(peopleWebModel.idUser())
                .personName(peopleWebModel.personName())
                .personLastname(peopleWebModel.personLastname())
                .personProfileImageUrl(peopleWebModel.personProfileImageUrl())
                .personDni(peopleWebModel.personDni())
                .personMobilePhone(peopleWebModel.personMobilePhone())
                .personGender(peopleWebModel.personGender())
                .personInstituteCampus(peopleWebModel.personInstituteCampus())
                .personInstitutionalEmail(peopleWebModel.personInstitutionalEmail())
                .personInstitutionalCareer(peopleWebModel.personInstitutionalCareer())
                .personCurrentTerm(peopleWebModel.personCurrentTerm())
                .build();
    }

    @Override
    public PeopleWebModel toWeb(PeopleModel peopleModel) {
        return new PeopleWebModel(
                peopleModel.getPersonId(),
                peopleModel.getIdUser(),
                peopleModel.getPersonName(),
                peopleModel.getPersonLastname(),
                peopleModel.getPersonProfileImageUrl(),
                peopleModel.getPersonDni(),
                peopleModel.getPersonMobilePhone(),
                peopleModel.getPersonGender(),
                peopleModel.getPersonInstituteCampus(),
                peopleModel.getPersonInstitutionalEmail(),
                peopleModel.getPersonInstitutionalCareer(),
                peopleModel.getPersonCurrentTerm()
        );
    }
}