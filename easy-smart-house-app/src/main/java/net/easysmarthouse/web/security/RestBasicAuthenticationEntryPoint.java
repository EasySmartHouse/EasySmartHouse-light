package net.easysmarthouse.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.easysmarthouse.util.CustomError;
import net.easysmarthouse.util.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(
                new CustomError(ErrorType.ACCESS_DENIED, "Invalid user credentials")
        ));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setRealmName("eshRealm");
        super.afterPropertiesSet();
    }

}
