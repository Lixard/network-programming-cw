package ru.maxim.borisov.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Начальная точнка запуска Spring Boot.
 */
@SpringBootApplication(scanBasePackages = "ru.maxim.borisov.messenger")
@EntityScan(basePackages = "ru.maxim.borisov.messenger.entity")
@EnableJpaRepositories(basePackages = "ru.maxim.borisov.messenger.repository")
public class MessengerApp {
    public static void main(String[] args) {
        new SpringApplication(MessengerApp.class).run(args);
    }
}
