package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;

import java.util.List;

/**
 * Сервис для взаимодействия с сущностью чата.
 */
public interface ChatService {

    /**
     * Получить список всех чатов, в которых участвует пользователь.
     *
     * @param userId идентификатор пользователя
     * @return список чатов
     */
    List<ChatGetDto> getCurrentUserChats(Long userId);

    /**
     * Создание нового чата.
     *
     * @param chatCreateDto информация о чате (название и участники)
     * @return сохраненный чат
     */
    ChatGetDto create(ChatCreateDto chatCreateDto);

    /**
     * Обновление чата.
     *
     * @param chatUpdateDto обновленная версия чата
     * @return сохраненный чат
     */
    ChatGetDto update(ChatUpdateDto chatUpdateDto);

    /**
     * Удаление чата по идентификатору.
     *
     * @param chatId идентификатор чата
     */
    void delete(Long chatId);
}
