package ru.maxim.borisov.messenger.entity;

import ru.maxim.borisov.messenger.entity.embedded.MessageUserKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Сущность базы данных, представляющая собой элемент таблицы статусов сообщений.
 */
@Entity
@Table(name = "messages_statuses")
public class MessageStatus {

    @EmbeddedId
    private MessageUserKey id;

    @ManyToOne
    @MapsId("message_id")
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    public MessageUserKey getId() {
        return id;
    }

    public void setId(MessageUserKey id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
