package ru.maxim.borisov.messenger.dto.create;

/**
 * Dto для создания сообщения.
 */
public class MessageCreateDto {

    private Long chatId;
    private Long senderId;
    private String content;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
