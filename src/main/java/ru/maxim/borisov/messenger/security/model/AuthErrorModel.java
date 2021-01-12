package ru.maxim.borisov.messenger.security.model;

public class AuthErrorModel {

    private boolean authenticated;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
