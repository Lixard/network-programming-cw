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

/**
 * {@inheritDoc}
 */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getChatUnreadMessagesCountByCurrentUser(Long chatId, Long userId) {
        return messageStatusRepository.countAllByMessageChatIdAndUserIdAndIsReadIsFalse(chatId, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MessageGetDto> getAllMessages(Long chatId) {
        return messageMapper.toGetDto(messageRepository.findAllByChatId(chatId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageGetDto sendMessage(MessageCreateDto message) {
        final var entity = messageMapper.fromCreateDto(message);
        final var sender = userRepository.findById(message.getSenderId()).orElse(null);
        entity.setUser(sender);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void markChatMessagesAsRead(Long chatId, Long userId) {
        final var unreadMessages =
                messageStatusRepository.getAllByMessageChatIdAndUserIdAndIsReadIsFalse(chatId, userId);
        unreadMessages.forEach(message -> message.setRead(true));
        messageStatusRepository.saveAll(unreadMessages);
    }

}
