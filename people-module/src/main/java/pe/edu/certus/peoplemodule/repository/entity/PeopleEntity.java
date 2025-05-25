package pe.edu.certus.peoplemodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Transient
    @Column(name = "id_user")
    private Long idUser;

    @Transient
    @Column(name = "id_career")
    private Long idCareer;

    @Column(name = "person_name", nullable = false, length = 100)
    private String personName;

    @Column(name = "person_lastname", nullable = false, length = 100)
    private String personLastname;

    @Column(name = "person_dni", length = 8)
    private String personDni;

    @Column(name = "person_mobile_phone", length = 15)
    private String personMobilePhone;

    @Column(name = "person_gender", length = 1)
    private String personGender;

    @Column(name = "person_institute_location", length = 100)
    private String personInstituteLocation;

    @Column(name = "person_institutional_email", length = 100)
    private String personInstitutionalEmail;

    @Column(name = "person_institutional_cycle")
    private Integer personInstitutionalCycle;
}