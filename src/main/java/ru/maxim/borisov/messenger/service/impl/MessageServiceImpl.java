package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.entity.MessageStatus;
import ru.maxim.borisov.messenger.entity.embedded.MessageUserKey;
import ru.maxim.borisov.messenger.mapper.MessageMapper;
import ru.maxim.borisov.messenger.repository.MessageRepository;
import ru.maxim.borisov.messenger.repository.MessageStatusRepository;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final MessageStatusRepository messageStatusRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageMapper messageMapper,
                              MessageStatusRepository messageStatusRepository,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.messageStatusRepository = messageStatusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId) {
        return messageStatusRepository.countAllByMessageChatIdAndUserIdAndIsReadIsFalse(chatId, userId);
    }

    @Override
    public List<MessageGetDto> getAllMessages(Long chatId) {
        return messageMapper.toGetDto(messageRepository.findAllByChatId(chatId));
    }

    @Override
    public MessageGetDto sendMessage(MessageCreateDto message) {
        final var entity = messageMapper.fromCreateDto(message);
        final var saved = messageRepository.save(entity);
        final var chatUsers = userRepository.findAllByChatsId(message.getChatId());
        chatUsers.forEach(user -> {
            final var messageStatus = new MessageStatus();
            messageStatus.setId(new MessageUserKey(saved.getId(), user.getId()));
            messageStatus.setUser(user);
            messageStatus.setMessage(saved);
            messageStatus.setRead(message.getSenderId().equals(user.getId()));
            messageStatusRepository.save(messageStatus);
        });
        return messageMapper.toGetDto(saved);
    }

}
