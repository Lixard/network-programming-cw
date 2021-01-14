package ru.maxim.borisov.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.update.ChatUpdateDto;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.service.ChatService;
import ru.maxim.borisov.messenger.service.MessageService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/chats",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final CurrentUser currentUser;

    @Autowired
    public ChatController(ChatService chatService,
                          MessageService messageService,
                          CurrentUser currentUser) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.currentUser = currentUser;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGetDto create(@RequestBody ChatCreateDto chatCreateDto) {
        return chatService.create(chatCreateDto);
    }

    @GetMapping
    public List<ChatGetDto> getCurrentUserChats() {
        return chatService.getCurrentUserChats(currentUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGetDto update(Long chatId, @RequestBody ChatUpdateDto chatUpdateDto) {
        chatUpdateDto.setId(chatId);
        return chatService.update(chatUpdateDto);
    }

    @GetMapping("/{chatId}/unread")
    public Long getCurrentUserChatUnreadMessagesCount(@PathVariable Long chatId) {
        return messageService.getChatUnreadMessagesCountByCurrentUser(chatId, currentUser.getId());
    }

}
