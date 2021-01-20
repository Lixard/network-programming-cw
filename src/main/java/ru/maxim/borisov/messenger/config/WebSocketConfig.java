package ru.maxim.borisov.messenger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/ws");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").setAllowedOrigins("*").withSockJS();
    }

//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        final var resolver = new DefaultContentTypeResolver();
//        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//        final var converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        converter.setContentTypeResolver(resolver);
//        messageConverters.add(converter);
//        return false;
//    }
}
