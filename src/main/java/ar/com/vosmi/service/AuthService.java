package ar.com.vosmi.service;

import ar.com.vosmi.dto.UserDto;
import ar.com.vosmi.dto.response.JWTResponse;
import ar.com.vosmi.utils.DateUtils;
import ar.com.vosmi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.Long.parseLong;
import static java.lang.Long.valueOf;

@Service
public class AuthService {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private DateUtils dateUtils;

    @Value("${change.jwt.token.expires-in}")
    private int EXPIRES_IN;


    public JWTResponse login(String clientId, String clientSecret){

        UserDto userDto = UserDto.builder()
                .login("RUsbent")
                .firstName("Gunnar")
                .lastName("Matta")
                .activated(true)
                .commentary("NAda")
                .id(434L)
                .build();


        JWTResponse jwt = JWTResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtUtil.generateToken(userDto))
                .issuedAt(dateUtils.getDateMillis()+ "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return jwt;
    }
}
