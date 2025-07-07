package pe.edu.certus.usersmodule.logic.adapters.driver;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.usersmodule.logic.model.UserModel;
import pe.edu.certus.usersmodule.logic.ports.driver.ForUser;
import pe.edu.certus.usersmodule.logic.ports.mapper.ForMappingUser;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserAdapter {

    private final ForUser forUser;
    private final ForMappingUser forMappingUser;

    public UserAdapter(ForUser forUser, ForMappingUser forMappingUser) {
        this.forUser = forUser;
        this.forMappingUser = forMappingUser;
    }

    @GetMapping("/me")
    public ResponseEntity<UserWebModel> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Long userId = Long.parseLong(authentication.getName());
            UserModel userModel = (UserModel) forUser.findUserById(userId);
            UserWebModel response = forMappingUser.toWeb(userModel);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserWebModel> createUser(@Valid @RequestBody UserWebModel userWebModel) {
        try {
            UserModel objectFromWeb = forMappingUser.fromWeb(userWebModel);
            forUser.createUser(objectFromWeb);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ERROR CREATING USER", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWebModel> findUserById(@PathVariable Long id) {
        try {
            UserModel userModel = (UserModel) forUser.findUserById(id);
            UserWebModel response = forMappingUser.toWeb(userModel);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-role/{userId}/{roleId}")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        try {
            UserModel user = (UserModel) forUser.findUserById(userId);
            user.setIdRole(roleId);
            forUser.updateUser(user);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        try {
            forUser.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserWebModel>> findAllUsers() {
        try {
            List<UserModel> userModels = forUser.findAllUsers();
            List<UserWebModel> response = userModels.stream()
                    .map(forMappingUser::toWeb)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("ERROR FINDING ALL USERS", e);
        }
    }
}