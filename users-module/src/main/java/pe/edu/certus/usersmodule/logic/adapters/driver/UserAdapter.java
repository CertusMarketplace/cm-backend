package pe.edu.certus.usersmodule.logic.adapters.driver;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.springframework.security.core.Authentication;
=======
import org.springframework.security.core.annotation.AuthenticationPrincipal;
>>>>>>> Stashed changes
=======
import org.springframework.security.core.annotation.AuthenticationPrincipal;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
=======
>>>>>>> Stashed changes
    @PutMapping("/update-role/{userId}/{roleId}")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        UserModel user = (UserModel) forUser.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        }
        user.setIdRole(roleId);
        forUser.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserWebModel> getCurrentUser(@AuthenticationPrincipal String userIdStr) {
        if (userIdStr == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            Long userId = Long.parseLong(userIdStr);
            UserModel userModel = (UserModel) forUser.findUserById(userId);
            UserWebModel response = forMappingUser.toWeb(userModel);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserWebModel> createUser(@Valid @RequestBody UserWebModel userWebModel) {
        UserModel objectFromWeb = forMappingUser.fromWeb(userWebModel);
        forUser.createUser(objectFromWeb);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWebModel> findUserById(@PathVariable Long id) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        try {
            UserModel userModel = (UserModel) forUser.findUserById(id);
            UserWebModel response = forMappingUser.toWeb(userModel);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
=======
        UserModel userModel = (UserModel) forUser.findUserById(id);
        UserWebModel response = forMappingUser.toWeb(userModel);
        return ResponseEntity.ok(response);
>>>>>>> Stashed changes
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
=======
        UserModel userModel = (UserModel) forUser.findUserById(id);
        UserWebModel response = forMappingUser.toWeb(userModel);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UserWebModel> updateUser(@Valid @RequestBody UserWebModel userWebModel) {
        UserModel objectFromWeb = forMappingUser.fromWeb(userWebModel);
        UserModel updatedUser = (UserModel) forUser.updateUser(objectFromWeb);
        UserWebModel response = forMappingUser.toWeb(updatedUser);
        return ResponseEntity.ok(response);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        forUser.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserWebModel>> findAllUsers() {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        try {
            List<UserModel> userModels = forUser.findAllUsers();
            List<UserWebModel> response = userModels.stream()
                    .map(forMappingUser::toWeb)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("ERROR FINDING ALL USERS", e);
        }
=======
=======
>>>>>>> Stashed changes
        List<UserModel> userModels = forUser.findAllUsers();
        List<UserWebModel> response = userModels.stream()
                .map(forMappingUser::toWeb)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}