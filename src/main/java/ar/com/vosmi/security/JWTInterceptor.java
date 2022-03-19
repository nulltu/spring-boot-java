package ar.com.vosmi.security;

import ar.com.vosmi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTUtil jwtUtil;

    @Value("${change.jwt.token.auth.path}")
    private String AUTH_PATH;

    @Value("#{'${change.jwt.excluded.path}'.split(',')}")
    private List<String>excluded;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean validate = false;
        String uri = request.getRequestURI();


        if(uri.equals(AUTH_PATH) || excluded(uri)){
            validate = true;
        }

        if(!validate && request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()){

            String token = request.getHeader("Authorization").replace("Bearer", "");

            validate = !jwtUtil.validateToken(token);
        }

        if(!validate){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return validate;
    }

    private boolean excluded(String path){

        boolean result = false;

        for(String exc: excluded){
            if(!exc.equals("#") && exc.equals(path)){
                result = true;
            }
        }

        return  result;
    }

}
