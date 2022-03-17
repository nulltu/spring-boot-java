package ar.com.vosmi.controller;

import ar.com.vosmi.domain.User;
import ar.com.vosmi.service.AuthService;
import ar.com.vosmi.service.UserService;
import ar.com.vosmi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1.0")
public class AuthController {
    private static final String LOGIN = "/login";

    private UserService userService;

    private JWTUtil jwtUtil;

    private AuthService authService;


    @PostMapping(
            path = "oauth/client_credential/accesstoken",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object>signIn(@RequestBody MultiValueMap<String,String>paramMap, @RequestParam("grant_type")String grantType){
        return ResponseEntity.ok(authService.signIn(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }

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
