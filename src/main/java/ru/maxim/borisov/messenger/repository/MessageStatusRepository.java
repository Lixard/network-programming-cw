package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.MessageStatus;
import ru.maxim.borisov.messenger.entity.embedded.MessageUserKey;

import java.util.List;

/**
 * Spring data репозиторий для взаимодействия с таблицей статусов сообщений.
 */
public interface MessageStatusRepository extends JpaRepository<MessageStatus, MessageUserKey> {

    /**
     * Метод для подсчета всех непрочитанных сообщений определенным пользователем в определенном чате.
     *
     * @param chatId идентификатор чата
     * @param userId идентификатор пользователя
     * @return число
     */
    Long countAllByMessageChatIdAndUserIdAndIsReadIsFalse(Long chatId, Long userId);


    /**
     * Метод для получения всех статусов непрочитанных сообщений в чате. Используется для получения списка
     * непрочитанных сообщений и последующего преобразования их в прочитанные.
     *
     * @param chatId идентификатор чата
     * @param userId идентификатор пользователя
     * @return список статусов сообщений
     */
    List<MessageStatus> getAllByMessageChatIdAndUserIdAndIsReadIsFalse(Long chatId, Long userId);
}
