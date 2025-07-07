package pe.edu.certus.authmodule.logic.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String lastname;
}