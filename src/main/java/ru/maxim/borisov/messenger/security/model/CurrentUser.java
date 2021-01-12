package ru.maxim.borisov.messenger.security.model;

public interface CurrentUser {

    Long getId();

    String getUsername();

    boolean getAuthenticated();
}
