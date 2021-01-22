package ru.maxim.borisov.messenger.security.model;

/**
 * Интерфейс текущего пользователя системы.
 */
public interface CurrentUser {

    Long getId();

    String getUsername();

    boolean getAuthenticated();
}
