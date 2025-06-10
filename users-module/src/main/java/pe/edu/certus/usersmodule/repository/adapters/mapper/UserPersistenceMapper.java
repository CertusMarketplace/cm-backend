package pe.edu.certus.usersmodule.repository.adapters.mapper;

import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.repository.entity.UserEntity;
import pe.edu.certus.usersmodule.repository.ports.mapper.ForBridgingUser;

public class UserPersistenceMapper implements ForBridgingUser {

    @Override
    public UserEntity toPersistence(UserModel userModel) {
        return UserEntity.builder()
                .userId(userModel.getUserId())
                .idRole(userModel.getIdRole())
                .userEmail(userModel.getUserEmail())
                .userPassword(userModel.getUserPassword())
                .userCreatedAt(userModel.getUserCreatedAt())
                .userUpdatedAt(userModel.getUserUpdatedAt())
                .userStatus(userModel.getUserStatus())
                .build();
    }

    @Override
    public UserModel fromPersistence( UserEntity userEntity) {
        return UserModel.builder()
                .userId(userEntity.getUserId())
                .idRole(userEntity.getIdRole())
                .userEmail(userEntity.getUserEmail())
                .userPassword(userEntity.getUserPassword())
                .userCreatedAt(userEntity.getUserCreatedAt())
                .userUpdatedAt(userEntity.getUserUpdatedAt())
                .userStatus(userEntity.getUserStatus())
                .build();
    }
}
