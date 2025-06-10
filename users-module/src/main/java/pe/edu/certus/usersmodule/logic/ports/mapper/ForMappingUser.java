package pe.edu.certus.usersmodule.logic.ports.mapper;

import pe.edu.certus.usersmodule.logic.adapters.driver.UserWebModel;
import pe.edu.certus.usersmodule.logic.model.UserModel;

public interface ForMappingUser {
    UserModel fromWeb( UserWebModel userWebModel);
    UserWebModel toWeb( UserModel userModel);
}
