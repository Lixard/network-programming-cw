package ru.maxim.borisov.messenger.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Обработчик ситуации успешного выхода пользователя из системы. Заменяем дефолтный спринговский на самописный и
 * рест-апишный по аналогии с другими.
 */
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
