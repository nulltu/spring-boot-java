package ar.com.vosmi.service;

import ar.com.vosmi.dto.response.JWTResponse;
import ar.com.vosmi.utils.DateUtils;
import ar.com.vosmi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private JWTUtil jwtUtil;
    private DateUtils dateUtils;

    @Value("${change.jwt.token.expires-in}")
    private int EXPIRES_IN;

    public AuthService(JWTUtil jwtUtil, DateUtils dateUtils) {
        this.jwtUtil = jwtUtil;
        this.dateUtils = dateUtils;
    }

    public JWTResponse login(String clientId, String clientSecret){

        JWTResponse jwt = JWTResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtUtil.generateToken("Hola mundo desde AUTH SERVICE"))
                .issuedAt(dateUtils.getDateMillis()+ "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return jwt;
    }
}
