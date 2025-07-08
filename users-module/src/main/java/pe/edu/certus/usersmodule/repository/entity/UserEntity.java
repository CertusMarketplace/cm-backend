// Archivo: users-module/src/main/java/pe/edu/certus/usersmodule/repository/entity/UserEntity.java
package pe.edu.certus.usersmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Column( name = "id_role" )
=======
    @Column(name = "id_role")
>>>>>>> Stashed changes
=======
    @Column(name = "id_role")
>>>>>>> Stashed changes
=======
    @Column(name = "id_role")
>>>>>>> Stashed changes
    private Long idRole;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_created_at")
    private LocalDateTime userCreatedAt;

    @Column(name = "user_updated_at")
    private LocalDateTime userUpdatedAt;

    @Column(name = "user_status")
    private Boolean userStatus;
}