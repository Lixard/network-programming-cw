package ru.maxim.borisov.messenger.security.model;

/**
 * Модель, представляющая собой ответ сервера на успешную авторизацию.
 */
public class LoginSuccessModel implements CurrentUser {

    private long id;
    private String username;
    private boolean authenticated;

    public LoginSuccessModel() {
    }

    public LoginSuccessModel(CurrentUser principal) {
        this.id = principal.getId();
        this.username = principal.getUsername();
        this.authenticated = principal.getAuthenticated();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
