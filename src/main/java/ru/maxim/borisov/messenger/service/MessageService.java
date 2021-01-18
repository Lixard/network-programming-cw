package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;

import java.util.List;

public interface MessageService {
    Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId);

    List<MessageGetDto> getAllMessages(Long chatId);

    MessageGetDto sendMessage(MessageCreateDto message);

    void markChatMessagesAsRead(Long chatId, Long userId);
}
