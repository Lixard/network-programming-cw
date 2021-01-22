package ru.maxim.borisov.messenger.dto.get;

/**
 * Dto для получения информации о загруженной аватарке профиля. Используется как подтверждения обновления аватарки, и
 * показывается когда мы регистрируем новую аватарку пользователю.
 */
public class ProfilePictureGetDto {

    private Long id;
    private String name;
    private long size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
