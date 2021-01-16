package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.get.MessageGetDto;

import java.util.List;

public interface MessageService {
    Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId);

    List<MessageGetDto> getAllMessages(Long chatId);
}
