package ru.maxim.borisov.messenger.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.security.model.LoginSuccessModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (!(authentication.getPrincipal() instanceof CurrentUser)) {
            throw new AuthenticationServiceException("User instanse not of LoginInfo");
        }
        final var principal = (CurrentUser) authentication.getPrincipal();
        final var loginSuccessModel = new LoginSuccessModel(principal);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(OBJECT_MAPPER.writeValueAsString(loginSuccessModel));
    }
}
