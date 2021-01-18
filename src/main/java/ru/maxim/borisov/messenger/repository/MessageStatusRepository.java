package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.MessageStatus;
import ru.maxim.borisov.messenger.entity.embedded.MessageUserKey;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, MessageUserKey> {
    Long countAllByMessageChatIdAndUserIdAndIsReadIsFalse(Long chatId, Long userId);
}
