package pe.edu.certus.usersmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfileModel {
    private Long userId;
    private Long idRole;
    private String userEmail;
    private String personName;
    private String personLastname;
    private String personProfileImageUrl;
}