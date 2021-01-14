package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Long countByChatIdAndUserIdAndMessageStatusesIsReadFalse(Long chatId, Long userId);
}
