package ar.com.vosmi.controller;

import ar.com.vosmi.config.Constants;
import ar.com.vosmi.domain.User;
import ar.com.vosmi.service.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private static final String USERS_ID = "/users/{id:" + Constants.LOGIN_REGEX + "}";

    private static final String USERS = "/users";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*** Find all users */
    @GetMapping(USERS)
    public List<User> users() {
        return  userService.getUsers();
    }

    /*** Get user by id */
    @GetMapping(USERS_ID)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /*** Create new user */
    @PostMapping(USERS)
    public void createUser(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passwordHash =  argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(passwordHash);
        userService.createUser(user);
    }

    /*** Update user*/
    @PutMapping(USERS)
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    /*** Delete user by id */
    @DeleteMapping(USERS_ID)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
