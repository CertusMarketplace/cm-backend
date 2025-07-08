package pe.edu.certus.usersmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.logic.ports.driven.ForManagingUser;
import pe.edu.certus.usersmodule.logic.ports.driver.ForUser;
import java.util.List;

@Service
public class UserManager implements ForUser<UserModel, Long> {
    private final ForManagingUser forManagingUser;

    public UserManager(ForManagingUser forManagingUser) {
        this.forManagingUser = forManagingUser;
    }

    @Override
    public void createUser(UserModel userModel) { forManagingUser.satisfyCreateUser(userModel); }
    @Override
    public UserModel findUserById(Long id) { return forManagingUser.satisfyFindUserById(id); }
    @Override
    public List<UserModel> findAllUsers() { return forManagingUser.satisfyFindAllUser(); }
    @Override
    public UserModel updateUser(UserModel userModel) { return forManagingUser.satisfyUpdateUser(userModel); }
    @Override
    public void deleteUser(Long id) { forManagingUser.satisfyDeleteUserById(id); }
}