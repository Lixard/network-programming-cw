package ru.maxim.borisov.messenger.service;

public interface MessageService {
    Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId);
}
