package ar.com.vosmi.controller;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    @RequestMapping("/users")
    public List<User> users() {
        return  userRepository.findAll();
    }

    @RequestMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("RUsbent");
        user.setLastName("Matta");
        user.setDocumentNumber(94246680L);
        user.setEmail("rusbent.matta@gmail.com");
        user.setCommentary("PRUEBA DE USUARIO REST");

        return user;
    }
}
