package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;
import ru.maxim.borisov.messenger.entity.Chat;
import ru.maxim.borisov.messenger.entity.User;
import ru.maxim.borisov.messenger.mapper.ChatMapper;
import ru.maxim.borisov.messenger.repository.ChatRepository;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.service.ChatService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatServiceImpl(
            ChatRepository chatRepository,
            UserRepository userRepository,
            ChatMapper chatMapper,
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatMapper = chatMapper;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatGetDto create(ChatCreateDto chatCreateDto) {
        final var chat = chatMapper.fromCreateDto(chatCreateDto);
        final var createdBy = chat.getCreatedBy();
        final var users = formUsersFromIds(chatCreateDto.getParticipationIds());
        users.add(createdBy);
        chat.setUsers(users);

        final var saved = chatRepository.save(chat);
        final var chatGetDto = chatMapper.toGetDto(saved);

        chatCreateDto.getParticipationIds().add(createdBy.getId());
        chatCreateDto.getParticipationIds()
                .forEach(userId ->
                        simpMessagingTemplate.convertAndSend("/topic/users/" + userId + "/chat-create", chatGetDto));
        return chatGetDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatGetDto update(ChatUpdateDto chatUpdateDto) {
        final var chat = chatRepository.findById(chatUpdateDto.getId()).orElseThrow();
        chat.setName(chatUpdateDto.getName());
        chat.setUsers(formUsersFromIds(chatUpdateDto.getParticipationIds()));
        final var saved = chatRepository.saveAndFlush(chat);
        return chatMapper.toGetDto(saved);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long chatId) {
        chatRepository.findById(chatId)
                .map(Chat::getUsers)
                .stream()
                .flatMap(Collection::stream)
                .map(User::getId)
                .forEach(userId -> simpMessagingTemplate
                        .convertAndSend("/topic/users/" + userId + "/chat-remove", chatId));
        chatRepository.deleteById(chatId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatGetDto> getCurrentUserChats(Long userId) {
        final var chats = chatRepository.findByUsersId(userId);
        return chatMapper.toGetDto(chats);
    }

    /**
     * Метод для преобразования списка пользовательских идентификаторов в самих пользователей.
     *
     * @param userIds список идентификаторов пользователей
     * @return список пользователей
     */
    private List<User> formUsersFromIds(List<Long> userIds) {
        return userIds.stream().map(id -> {
            final var user = userRepository.findById(id).orElseThrow(NullPointerException::new);
            user.setId(id);
            return user;
        }).collect(Collectors.toList());
    }
}
