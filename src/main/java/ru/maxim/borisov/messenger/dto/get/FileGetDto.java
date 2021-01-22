package ru.maxim.borisov.messenger.dto.get;

/**
 * Dto для получения информации о файле. Обычно используется для получения списка файлов.
 * Передача файлов сама по себе крайне дорогая процедура, поэтому это позволяет нам сократить расходы на передачу
 * файлов.
 */
public class FileGetDto {
    private Long id;
    private Long messageId;
    private String name;
    private String mimeType;
    private Long size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
