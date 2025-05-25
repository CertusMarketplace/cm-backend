package pe.edu.certus.peoplemodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PeopleModel {
    private Long personId;
    private Long idUser;
    private Long idCareer;
    private String personName;
    private String personLastname;
    private String personDni;
    private String personMobilePhone;
    private String personGender;
    private String personInstituteLocation;
    private String personInstitutionalEmail;
    private Integer personInstitutionalCycle;
}