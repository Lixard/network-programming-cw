package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.mapper.MessageMapper;
import ru.maxim.borisov.messenger.repository.MessageRepository;
import ru.maxim.borisov.messenger.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId) {
        return messageRepository.countByChatIdAndUserIdAndMessageStatusesIsReadFalse(chatId, userId);
    }

    @Override
    public List<MessageGetDto> getAllMessages(Long chatId) {
        return messageMapper.toGetDto(messageRepository.findAllByChatId(chatId));
    }
}
