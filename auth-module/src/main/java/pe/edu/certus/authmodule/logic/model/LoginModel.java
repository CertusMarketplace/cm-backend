package pe.edu.certus.authmodule.logic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("userPassword")
    private String userPassword;
}