package pe.edu.certus.authmodule.logic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterModel {
    private String userEmail;
    private String userPassword;
    private String personName;
    private String personLastname;
}