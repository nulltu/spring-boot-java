package ar.com.vosmi.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "change.jwt")
public class JWTIOProperties {

    private Security security;
    private String timezone;
    private String issuer;
    private TokenJWT tokenJWT;
    private Excluded excluded;

    @Data
    public static class Security{
        private boolean enabled;
    }

    @Data
    public static class TokenJWT{
        private Auth auth;
        private String secret;
        private int expiresIn;
    }

    @Data
    public static class Auth{
        private String path;
    }

    @Data
    public static class Excluded{
        private String path;
    }
}
