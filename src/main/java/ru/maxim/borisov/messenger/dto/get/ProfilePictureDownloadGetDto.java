package ru.maxim.borisov.messenger.dto.get;

import java.util.Arrays;

/**
 * Dto для получения аватрки профиля.
 */
public class ProfilePictureDownloadGetDto {
    private Long id;
    private String type;
    private String name;
    private byte[] data;

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

    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public void setData(byte[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
