package ru.maxim.borisov.messenger.dto.create;

/**
 * Dto для создания пользователя.
 */
public class UserCreateDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
