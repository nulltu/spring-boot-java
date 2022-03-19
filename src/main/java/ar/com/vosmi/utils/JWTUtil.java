package ar.com.vosmi.utils;

import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
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

    @Value("${change.jwt.token.expires-in:36000}")
    private int EXPIRES_IN;

    @Value("${change.jwt.issuer:none}")
    private String ISSUER;


    public String generateToken(Object src) {

        String subject = GsonUtils.serializae(src);
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

        JWT jwt = JWTUtils.decodePayload(encodedJWT);

        return jwt.isExpired();
    }

    public String getPayload(String encodedJWT) {

        JWT jwt = jwt(encodedJWT);

        return jwt.subject;
    }

    private JWT jwt(String encodedJWT){

        Verifier verifier = HMACVerifier.newVerifier(SECRET);

        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}