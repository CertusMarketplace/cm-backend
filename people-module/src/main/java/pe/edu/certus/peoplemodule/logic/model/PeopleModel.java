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
    private String personName;
    private String personLastname;
    private String personProfileImageUrl;
    private String personDni;
    private String personMobilePhone;
    private String personGender;
    private String personInstituteCampus;
    private String personInstitutionalEmail;
<<<<<<< Updated upstream
    private String personInstitutionalCareer;
    private Integer personCurrentTerm;
=======
    private Integer personInstitutionalCycle;
    private String personInstitutionalCareer;
>>>>>>> Stashed changes
}