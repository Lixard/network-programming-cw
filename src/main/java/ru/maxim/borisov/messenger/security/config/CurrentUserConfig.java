package ru.maxim.borisov.messenger.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import ru.maxim.borisov.messenger.security.model.CurrentUser;

/**
 * Конфигурация в которой создается бин(объект) текущего авторизованного пользователя  в системе.
 * Является проксируемым, что означает, что новый объект создается на каждый запрос к нашему api. Это гарантирует нам
 * то, что при каждом вызове текущего пользователя у нас будет появляться именно тот пользователь, который делал запрос.
 */
@Configuration
public class CurrentUserConfig {

    private static final String ANONYMOUS_USER = "anonymousUser";

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CurrentUser currentUser() {
        final var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == ANONYMOUS_USER) {
            return new CurrentUser() {
                @Override
                public Long getId() {
                    return -1L;
                }

                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public boolean getAuthenticated() {
                    return false;
                }
            };
        }
        return (CurrentUser) principal;
    }
}
