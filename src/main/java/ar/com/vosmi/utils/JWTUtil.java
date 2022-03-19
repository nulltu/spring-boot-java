package ar.com.vosmi.utils;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.TimeZone;


@Component
public class JWTUtil {
    @Value("${change.jwt.token.secret:secret}")
    private String SECRET;

    @Value("${change.jwt.timezone:UTC}")
    private String TIMEZONE;

    @Value("${change.jwt.expires-in:36000}")
    private int EXPIRES_IN;

    @Value("${change.jwt.issuer:none}")
    private String ISSUER;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtil.class);

    public String generateToken(Object src) {

        String subject = GsonUtils.serialize(src);
        Signer signer = HMACSigner.newSHA256Signer(SECRET);
        TimeZone tz = TimeZone.getTimeZone(TIMEZONE);
        ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRES_IN);

        JWT jwt = new JWT()
                .setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
                .setSubject(subject)
                .setExpiration(zdt);

        return JWT.getEncoder().encode(jwt, signer);
    }

    public boolean validateToken(String encodedJWT) {
        return false;
    }

    public String getPayload(String encodeJWT) {
        return null;
    }

    private JWT jwt(String encodedJWT){

        Verifier verifier = HMACVerifier.newVerifier(SECRET);

        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}