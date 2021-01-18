package ru.maxim.borisov.messenger.entity.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MessageUserKey implements Serializable {

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id")
    private Long userId;

    public MessageUserKey() {
    }

    public MessageUserKey(Long messageId, Long userId) {
        this.messageId = messageId;
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageUserKey that = (MessageUserKey) o;
        return Objects.equals(messageId, that.messageId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, userId);
    }
}
