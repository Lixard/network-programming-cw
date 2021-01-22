package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;

import java.util.List;

/**
 * Сервис, отвечающий за взаимодействие с сущностью сообщейний.
 */
public interface MessageService {
    /**
     * Получить количество непрочитанных сообщений определенного пользователя в определенном чате.
     *
     * @param chatId идентификатор чата
     * @param userId идентификатор пользователя
     * @return число
     */
    Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId);

    /**
     * Получить все сообщения определенного чата.
     *
     * @param chatId идентификатор чата
     * @return список сообщений
     */
    List<MessageGetDto> getAllMessages(Long chatId);

    /**
     * Отправить сообщение на сервер
     *
     * @param message сообщение
     * @return сохраненное в системе сообщение
     */
    MessageGetDto sendMessage(MessageCreateDto message);

    /**
     * Позволяет отметить сообщения в чате пользователя как прочитанные.
     *
     * @param chatId идентификатор чата
     * @param userId идентификатор пользователя
     */
    void markChatMessagesAsRead(Long chatId, Long userId);
}
