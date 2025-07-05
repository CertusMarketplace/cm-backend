package pe.edu.certus.authmodule.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email", unique = true, nullable = false)
    private String userEmail;
    
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "id_role")
    private Long idRole;

}