package pe.edu.certus.usersmodule.logic.ports.driver;

import java.util.List;

public interface ForUser < UserModel, Long>  {
    void createUser(UserModel userModel);
    UserModel findUserById(Long id);
    List<UserModel> findAllUsers();
    UserModel updateUser(UserModel userModel);
    void deleteUser(Long id);
}
