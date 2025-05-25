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
                .idCareer(peopleWebModel.idCareer())
                .personName(peopleWebModel.personName())
                .personLastname(peopleWebModel.personLastname())
                .personDni(peopleWebModel.personDni())
                .personMobilePhone(peopleWebModel.personMobilePhone())
                .personGender(peopleWebModel.personGender())
                .personInstituteLocation(peopleWebModel.personInstituteLocation())
                .personInstitutionalEmail(peopleWebModel.personInstitutionalEmail())
                .personInstitutionalCycle(peopleWebModel.personInstitutionalCycle())
                .build();
    }

    @Override
    public PeopleWebModel toWeb(PeopleModel peopleModel) {
        return new PeopleWebModel(
                peopleModel.getPersonId(),
                peopleModel.getIdUser(),
                peopleModel.getIdCareer(),
                peopleModel.getPersonName(),
                peopleModel.getPersonLastname(),
                peopleModel.getPersonDni(),
                peopleModel.getPersonMobilePhone(),
                peopleModel.getPersonGender(),
                peopleModel.getPersonInstituteLocation(),
                peopleModel.getPersonInstitutionalEmail(),
                peopleModel.getPersonInstitutionalCycle()
        );
    }
}