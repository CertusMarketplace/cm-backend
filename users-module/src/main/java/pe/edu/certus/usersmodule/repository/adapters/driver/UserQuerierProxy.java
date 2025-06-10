package pe.edu.certus.usersmodule.repository.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.logic.ports.driven.ForManagingUser;
import pe.edu.certus.usersmodule.repository.entity.UserEntity;
import pe.edu.certus.usersmodule.repository.ports.driver.ForQueryingUser;
import pe.edu.certus.usersmodule.repository.ports.mapper.ForBridgingUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserQuerierProxy implements ForManagingUser {
    private final ForQueryingUser forQueryingUser;
    private final ForBridgingUser forBridgingUser;

    public UserQuerierProxy ( ForQueryingUser forQueryingUser, ForBridgingUser forBridgingUser ) {
        this.forQueryingUser = forQueryingUser;
        this.forBridgingUser = forBridgingUser;
    }

    @Override
    public void satisfyCreateUser( UserModel userModel ){
        UserEntity objectFromDomain = forBridgingUser.toPersistence(userModel);
        forQueryingUser.save(objectFromDomain);
        System.out.print( "USER ENTITY HAS BEEN CREATED SUCCESSFULLY!" );
    }

    @Override
    public UserModel satisfyFindUserById( Long id) {
        return forQueryingUser.findById(id)
                .map(forBridgingUser::fromPersistence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "USER ENTITY NOT FOUND WITH ID: " + id));
    }
    @Override
    public UserModel satisfyUpdateUser(UserModel userModel) {
        UserEntity objectFromDomain = forBridgingUser.toPersistence(userModel);

        Optional<UserEntity> existingUser = forQueryingUser.findById(objectFromDomain.getUserId());
        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("USER NOT FOUND WITH ID: " + objectFromDomain.getUserId());
        }

        UserEntity updatedEntity = forQueryingUser.save(objectFromDomain);
        System.out.println("USER ENTITY HAS BEEN UPDATED SUCCESSFULLY");

        return forBridgingUser.fromPersistence(updatedEntity);
    }

    @Override
    public void satisfyDeleteUserById(Long id) {
        Optional<UserEntity> existingUser = forQueryingUser.findById(id);
        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("USER NOT FOUND WITH ID: " + id);
        }

        forQueryingUser.deleteById(id);
        System.out.println("USER ID: " + id + " HAS BEEN DELETED");
    }

    @Override
    public List<UserModel> satisfyFindAllUser() {
        List<UserEntity> userEntities = forQueryingUser.findAll();
        System.out.println("ALL USER ENTITIES HAVE BEEN FOUND SUCCESSFULLY");

        return userEntities.stream()
                .map(forBridgingUser::fromPersistence)
                .collect( Collectors.toList());
    }
}
