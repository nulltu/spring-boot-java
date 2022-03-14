package ar.com.vosmi.controller;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.service.UserService;
import ar.com.vosmi.utils.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final String LOGIN = "/login";

    private UserService userService;

    private JWTUtil jwtUtil;

    public AuthController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(LOGIN)
    public String login(@RequestBody User user){
        User userlogged = userService.verifiedCredentialsAndGetUser(user);
        if(userlogged != null){
            String tokenJWT = jwtUtil.create(String.valueOf(userlogged.getId()), userlogged.getEmail());
            return tokenJWT;
        }else
            return "FAIL";
    }
}
