package ar.com.vosmi.controller;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*** Find all users */
    @GetMapping("/users")
    public List<User> users() {
        return  userService.getUsers();
    }

    /*** Get user by id */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /*** Create new user */
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    /*** Update user by id */
    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    /*** Delete user by id */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
