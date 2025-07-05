package pe.edu.certus.authmodule.logic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleLoginModel {
    @JsonProperty("id_token")
    private String idToken;
}