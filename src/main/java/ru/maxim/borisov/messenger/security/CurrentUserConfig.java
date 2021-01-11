package ru.maxim.borisov.messenger.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import ru.maxim.borisov.messenger.security.model.CurrentUser;

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
                    return null;
                }

                @Override
                public String getUsername() {
                    return null;
                }
            };
        }
        return (CurrentUser) principal;
    }
}
