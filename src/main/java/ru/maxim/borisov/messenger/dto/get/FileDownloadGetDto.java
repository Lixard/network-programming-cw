package ru.maxim.borisov.messenger.dto.get;

import java.util.Arrays;

public class FileDownloadGetDto {
    private Long id;
    private String name;
    private String mimeType;
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public void setData(byte[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }
}
