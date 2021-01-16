package ru.maxim.borisov.messenger.dto.get;

import java.time.Instant;

public class MessageGetDto {

    private Long id;
    private String content;
    private UserGetDto sender;
    private Instant sendDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserGetDto getSender() {
        return sender;
    }

    public void setSender(UserGetDto sender) {
        this.sender = sender;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public void setSendDate(Instant sendDate) {
        this.sendDate = sendDate;
    }
}
