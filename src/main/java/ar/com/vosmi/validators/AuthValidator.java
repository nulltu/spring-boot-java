package ar.com.vosmi.validators;

import ar.com.vosmi.exceptions.Apiunauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String,String> paramMap, String grantType) throws Apiunauthorized {
        if(grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)){
            message("El campo grant_type es invalido");
        }

        if(Objects.isNull(paramMap)
                || (paramMap.getFirst("client_id").isEmpty())
                || paramMap.getFirst("client_secret").isEmpty()){
            message("client_id y/o client_secret son invalidos");
        }

    }

    private void message(String message) throws Apiunauthorized {
        throw new Apiunauthorized(message);
    }
}
