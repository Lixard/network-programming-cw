package ru.maxim.borisov.messenger.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {
        if (response.getStatus() != HttpServletResponse.SC_FORBIDDEN) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
