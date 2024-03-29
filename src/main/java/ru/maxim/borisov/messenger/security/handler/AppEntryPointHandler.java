package ru.maxim.borisov.messenger.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.maxim.borisov.messenger.security.model.AuthErrorModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Обработчик для замены дефолтных спринговских. Так как у нас рест-апи, то и все служебные ответы об авторизации мы
 * хотим получать в виде JSON объектов. Этим хендлером мы и реализуем подобное поведение.
 */
public class AppEntryPointHandler implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (response.getStatus() != HttpServletResponse.SC_FORBIDDEN) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        final var errorModel = new AuthErrorModel();
        errorModel.setAuthenticated(false);
        response.getWriter().print(OBJECT_MAPPER.writeValueAsString(errorModel));
    }
}
