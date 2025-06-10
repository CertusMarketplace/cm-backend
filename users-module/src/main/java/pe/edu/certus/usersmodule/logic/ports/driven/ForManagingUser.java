package pe.edu.certus.usersmodule.logic.ports.driven;

import pe.edu.certus.usersmodule.logic.model.UserModel;

import java.util.List;

public interface ForManagingUser {
    void satisfyCreateUser( UserModel userModel);
    UserModel satisfyFindUserById(Long id);
    List<UserModel> satisfyFindAllUser();
    UserModel satisfyUpdateUser(UserModel userModel);
    void satisfyDeleteUserById(Long id);
}
