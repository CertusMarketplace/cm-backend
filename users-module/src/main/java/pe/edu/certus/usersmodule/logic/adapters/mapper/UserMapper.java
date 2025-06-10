package pe.edu.certus.usersmodule.logic.adapters.mapper;

import pe.edu.certus.usersmodule.logic.adapters.driver.UserWebModel;
import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.logic.ports.mapper.ForMappingUser;

public class UserMapper implements ForMappingUser {

    @Override
    public UserModel fromWeb( UserWebModel userWebModel) {
        return UserModel.builder()
                .userId(userWebModel.userId())
                .idRole(userWebModel.idRole())
                .userEmail(userWebModel.userEmail())
                .userPassword(userWebModel.userPassword())
                .userCreatedAt(userWebModel.userCreatedAt())
                .userUpdatedAt(userWebModel.userUpdatedAt())
                .userStatus(userWebModel.userStatus())
                .build();
    }

    @Override
    public UserWebModel toWeb( UserModel userModel) {
        return new UserWebModel(
                userModel.getUserId(),
                userModel.getIdRole(),
                userModel.getUserEmail(),
                userModel.getUserPassword(),
                userModel.getUserCreatedAt(),
                userModel.getUserUpdatedAt(),
                userModel.getUserStatus()
        );
    }
}
