package pe.edu.certus.usersmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.logic.ports.driven.ForManagingUser;
import pe.edu.certus.usersmodule.logic.ports.driver.ForUser;

import java.util.List;

@Service
public class UserManager implements ForUser< UserModel, Long > {

    private final ForManagingUser forManagingUser;

    public UserManager( ForManagingUser forManagingUser ) {
        this.forManagingUser = forManagingUser;
    }

    @Override
    public void createUser( UserModel userModel ) {
        try {
            forManagingUser.satisfyCreateUser( userModel );
            System.out.println( "THE USER HAS BEEN CREATED SUCCESSFULLY" );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "ERROR CREATING THE USER: " + e.getMessage() );
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findUserById( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            UserModel userModel = forManagingUser.satisfyFindUserById( id );
            System.out.println( "THE USER HAS BEEN FOUND SUCCESSFULLY" );

            if ( userModel == null ) {
                throw new IllegalArgumentException( "USER NOT FOUND" );
            }

            return userModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND USER MODEL", e );
        }
    }

    @Override
    public UserModel updateUser( UserModel userModel ) {
        try {
            if ( userModel == null ) {
                throw new IllegalArgumentException( "USER MODEL CANNOT BE NULL" );
            }

            UserModel updatedUserModel = forManagingUser.satisfyUpdateUser( userModel );
            System.out.println( "USER MODEL UPDATED SUCCESSFULLY" );

            return updatedUserModel;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO UPDATE USER MODEL", e );
        }
    }

    @Override
    public void deleteUser( Long id ) {
        try {
            if ( id == null ) {
                throw new IllegalArgumentException( "ID CANNOT BE NULL" );
            }

            forManagingUser.satisfyDeleteUserById( id );
            System.out.println( "USER MODEL DELETED SUCCESSFULLY" );

        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO DELETE USER MODEL", e );
        }
    }

    @Override
    public List< UserModel > findAllUsers( ) {
        try {
            List< UserModel > userModels = forManagingUser.satisfyFindAllUser( );
            System.out.println( "ALL USERS HAVE BEEN FOUND SUCCESSFULLY" );
            return userModels;
        } catch ( Exception e ) {
            throw new RuntimeException( "FAILED TO FIND ALL USER MODELS", e );
        }
    }
}
