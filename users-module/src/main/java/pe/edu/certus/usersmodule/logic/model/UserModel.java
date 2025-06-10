package pe.edu.certus.usersmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserModel {
    private Long userId;
    private Long idRole;
    private String userEmail;
    private String userPassword;
    private LocalDateTime userCreatedAt;
    private LocalDateTime userUpdatedAt;
    private Boolean userStatus;
}
