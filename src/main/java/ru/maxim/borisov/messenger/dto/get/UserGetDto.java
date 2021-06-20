package ru.maxim.borisov.messenger.dto.get;

/**
 * Dto для получения данных о пользователе. Используется чтобы спрятать пароль и прочую чувствительную инфромацию,
 * которую не обязательно знать всему приложению.
 */
public class UserGetDto {

    private Long id;
    private String username;
    private ProfilePictureDownloadGetDto picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfilePictureDownloadGetDto getPicture() {
        return picture;
    }

    public void setPicture(ProfilePictureDownloadGetDto picture) {
        this.picture = picture;
    }
}
