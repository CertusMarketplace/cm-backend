package pe.edu.certus.authmodule.configuration.annotations.utils;

import lombok.Data;

@Data
public class RequestSellerDto {
    private String password;

    private String name;
    private String lastname;
    private String email;
    private String dni;
    private String mobilePhone;
    private String gender;
    private String instituteCampus;
    private String institutionalCareer;
    private Integer institutionalCycle;
}