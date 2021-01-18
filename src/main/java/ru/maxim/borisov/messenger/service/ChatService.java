package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;

import java.util.List;

public interface ChatService {

    List<ChatGetDto> getCurrentUserChats(Long userId);

    ChatGetDto create(ChatCreateDto chatCreateDto);

    ChatGetDto update(ChatUpdateDto chatUpdateDto);

    void delete(Long chatId);
}
