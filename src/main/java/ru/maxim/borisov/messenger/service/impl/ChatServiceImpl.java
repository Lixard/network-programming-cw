package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;
import ru.maxim.borisov.messenger.entity.User;
import ru.maxim.borisov.messenger.mapper.ChatMapper;
import ru.maxim.borisov.messenger.repository.ChatRepository;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.service.ChatService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository,
                           UserRepository userRepository,
                           ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public ChatGetDto create(ChatCreateDto chatCreateDto) {
        final var chat = chatMapper.fromCreateDto(chatCreateDto);
        final var createdBy = chat.getCreatedBy();
        final var users = formUsersFromIds(chatCreateDto.getParticipationIds());
        users.add(createdBy);
        chat.setUsers(users);
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
    public void delete(Long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    public List<ChatGetDto> getCurrentUserChats(Long userId) {
        final var chats = chatRepository.findByUsersId(userId);
        return chatMapper.toGetDto(chats);
    }

    private List<User> formUsersFromIds(List<Long> userIds) {
        return userIds.stream().map(id -> {
            final var user = userRepository.findById(id).orElseThrow(NullPointerException::new);
            user.setId(id);
            return user;
        }).collect(Collectors.toList());
    }
}
