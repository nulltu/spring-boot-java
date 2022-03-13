package ar.com.vosmi.controller;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final String LOGIN = "/login";

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(LOGIN)
    public String login(@RequestBody User user){
        if(userService.verifiedCredentials(user)){
            return "OK";
        }else
            return "FAIL";
    }
}
