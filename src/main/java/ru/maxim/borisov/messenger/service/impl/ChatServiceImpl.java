package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.get.ChatWithLastUnreadMessageGetDto;
import ru.maxim.borisov.messenger.mapper.ChatMapper;
import ru.maxim.borisov.messenger.repository.ChatRepository;
import ru.maxim.borisov.messenger.service.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<ChatWithLastUnreadMessageGetDto> getChatsWithLastUnreadMessageByUserId(Long userId) {

        return ;
    }
}
