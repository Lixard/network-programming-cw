package ru.maxim.borisov.messenger.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Конфигурационный класс настройки безопасности, но только для веб-сокета.
 */
@Configuration
public class WsSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    /**
     * Во время работы с приложениям возникали ошибки CORS policy. Ушло много времени чтобы выяснить в чем проблема,
     * но по итогу оказалось, что фронтенд при взаимодействии с апи бэкенда отправляет запросы с одного адреса, а
     * получает на другой. Для того чтобы избавиться от ошибок нужно либо долго и упорно настраивать CSRF, либо, что
     * проще - просто отключить.
     * Этот метод как раз и позволяет отключить данную защиту.
     */
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }


}
