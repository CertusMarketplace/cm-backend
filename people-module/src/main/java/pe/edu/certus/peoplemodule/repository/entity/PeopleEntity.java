// Archivo: people-module/src/main/java/pe/edu/certus/peoplemodule/repository/entity/PeopleEntity.java
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Column(name = "id_user", nullable = false, unique = true)
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Column(name = "id_user")
>>>>>>> Stashed changes
    private Long idUser;

    @Column(name = "person_name", nullable = false, length = 100)
    private String personName;

    @Column(name = "person_lastname", nullable = false, length = 100)
    private String personLastname;

    @Column(name = "person_profile_image_url", length = 255)
    private String personProfileImageUrl;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Column(name = "person_dni", length = 8, unique = true)
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Column(name = "person_dni", length = 8)
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private Integer personCurrentTerm;
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private Integer personInstitutionalCycle;
>>>>>>> Stashed changes
}