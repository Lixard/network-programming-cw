package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.Message;

import java.util.List;

/**
 * Spring data репозиторий для взаимодействия с таблицей сообщений.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * Метод для поиска всех сообщений в чате.
     *
     * @param chatId идентификатор чата
     * @return список сообщений
     */
    List<Message> findAllByChatId(Long chatId);
}
