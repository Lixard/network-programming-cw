package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.get.ChatWithLastUnreadMessageGetDto;

import java.util.List;

public interface ChatService {
    List<ChatWithLastUnreadMessageGetDto> getChatsWithLastUnreadMessageByUserId(Long userId);
}
