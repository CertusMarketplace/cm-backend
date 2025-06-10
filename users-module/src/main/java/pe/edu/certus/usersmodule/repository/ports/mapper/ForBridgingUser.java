package pe.edu.certus.usersmodule.repository.ports.mapper;

import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.repository.entity.UserEntity;

public interface ForBridgingUser {
    UserModel fromPersistence( UserEntity userEntity);
    UserEntity toPersistence( UserModel userModel);
}
