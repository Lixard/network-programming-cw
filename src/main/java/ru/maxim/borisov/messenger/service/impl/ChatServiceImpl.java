package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.get.ChatWithLastUnreadMessageGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;
import ru.maxim.borisov.messenger.entity.Chat;
import ru.maxim.borisov.messenger.entity.User;
import ru.maxim.borisov.messenger.mapper.ChatMapper;
import ru.maxim.borisov.messenger.mapper.MessageMapper;
import ru.maxim.borisov.messenger.repository.ChatRepository;
import ru.maxim.borisov.messenger.repository.MessageRepository;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.service.ChatService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository,
                           UserRepository userRepository,
                           MessageRepository messageRepository,
                           ChatMapper chatMapper,
                           MessageMapper messageMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.chatMapper = chatMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public ChatGetDto create(ChatCreateDto chatCreateDto) {
        final var chat = chatMapper.fromCreateDto(chatCreateDto);
        chat.setUsers(formUsersFromIds(chatCreateDto.getParticipationIds()));
        final var saved = chatRepository.save(chat);
        return chatMapper.toGetDto(saved);
    }

    @Override
    public ChatGetDto update(ChatUpdateDto chatUpdateDto) {
        final var chat = chatRepository.findById(chatUpdateDto.getId()).orElseThrow();
        chat.setName(chatUpdateDto.getName());
        chat.setUsers(formUsersFromIds(chatUpdateDto.getParticipationIds()));
        final var saved = chatRepository.saveAndFlush(chat);
        return chatMapper.toGetDto(saved);
    }

    @Override
    public List<ChatWithLastUnreadMessageGetDto> getChatsWithLastUnreadMessageByUserId(Long userId) {
        final var chats = chatRepository.getAllByCreatedByIdOrderByMessagesSendDateDesc(userId);
        final var chatIds = chats.stream().map(Chat::getId).collect(Collectors.toList());
        final var unreadMessages = messageRepository.countAllWhereUnread(chatIds, userId);
        return formChatWithUnreadMessagesDto(chats, unreadMessages);
    }

    private List<User> formUsersFromIds(List<Long> userIds) {
        return userIds.stream().map(id -> {
            final var user = userRepository.findById(id).orElseThrow(NullPointerException::new);
            user.setId(id);
            return user;
        }).collect(Collectors.toList());
    }

    private List<ChatWithLastUnreadMessageGetDto> formChatWithUnreadMessagesDto(List<Chat> chats,
                                                                                List<Long> unreadMessagesCount) {
        final var resultDtoList = new ArrayList<ChatWithLastUnreadMessageGetDto>();
        for (var chat : chats) {
            final var resultDto = new ChatWithLastUnreadMessageGetDto();
            final var chatDto = chatMapper.toGetDto(chat);
            resultDto.setChat(chatDto);
            resultDto.setUnreadMessagesCount(unreadMessagesCount.get(0));
            final var message = chat.getMessages().get(0);
            resultDto.setLastMessage(messageMapper.toGetDto(message));
            resultDtoList.add(resultDto);
        }
        return resultDtoList;
    }
}
