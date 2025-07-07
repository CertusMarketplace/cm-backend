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

    @Column(name = "id_user", nullable = false, unique = true)
    private Long idUser;

    @Column(name = "person_name", nullable = false, length = 100)
    private String personName;

    @Column(name = "person_lastname", nullable = false, length = 100)
    private String personLastname;

    @Column(name = "person_profile_image_url", length = 255)
    private String personProfileImageUrl;

    @Column(name = "person_dni", length = 8, unique = true)
    private String personDni;

    @Column(name = "person_mobile_phone", length = 15)
    private String personMobilePhone;

    @Column(name = "person_gender", length = 1)
    private String personGender;

    @Column(name = "person_institute_campus", length = 100)
    private String personInstituteCampus;

    @Column(name = "person_institutional_email", length = 100, unique = true)
    private String personInstitutionalEmail;

    @Column(name = "person_institutional_career", length = 100)
    private String personInstitutionalCareer;

    @Column(name = "person_current_term")
    private Integer personCurrentTerm;
}