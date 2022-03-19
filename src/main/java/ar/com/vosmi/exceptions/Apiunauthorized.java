package ar.com.vosmi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Apiunauthorized extends Exception{

    private static final long serialVersionUID = 2579281837938543267L;

    public Apiunauthorized(String message){
        super(message);
    }
}
