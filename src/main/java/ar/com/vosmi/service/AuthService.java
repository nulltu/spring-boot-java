package ar.com.vosmi.service;

import ar.com.vosmi.dto.response.JWTResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public JWTResponse signIn(String clientId, String clientSecret){

        JWTResponse jwt = JWTResponse.builder()
                .tokenType("bearer")
                .accessToken("daskjljdaksldjas")
                .issuedAt(System.currentTimeMillis() + "")
                .clientId(clientId)
                .expiresIn(3600)
                .build();

        return jwt;
    }
}
