package ru.maxim.borisov.messenger.security.model;

public class LoginSuccessModel implements CurrentUser {

    private long id;
    private String username;

    public LoginSuccessModel() {
    }

    public LoginSuccessModel(CurrentUser principal) {
        this.id = principal.getId();
        this.username = principal.getUsername();
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
}
