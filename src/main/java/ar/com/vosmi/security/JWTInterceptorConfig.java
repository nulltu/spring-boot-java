package ar.com.vosmi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class JWTInterceptorConfig implements WebMvcConfigurer {

    @Value("${change.jwt.security.enabled:false}")
    private boolean securityEnabled;

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        if(securityEnabled){
            registry.addInterceptor(jwtInterceptor);
        }
    }

}
