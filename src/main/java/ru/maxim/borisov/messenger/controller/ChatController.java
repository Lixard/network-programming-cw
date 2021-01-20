package ru.maxim.borisov.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
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
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(ChatService chatService,
                          MessageService messageService,
                          CurrentUser currentUser,
                          SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.currentUser = currentUser;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGetDto create(@RequestBody ChatCreateDto chatCreateDto) {
        chatCreateDto.setCreatedBy(currentUser.getId());
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

    @GetMapping("/{chatId}/messages")
    public List<MessageGetDto> getAllMessages(@PathVariable Long chatId) {
        return messageService.getAllMessages(chatId);
    }

    @PostMapping("/{chatId}/mark-as-read")
    public void markChatMessagesAsRead(@PathVariable Long chatId) {
        messageService.markChatMessagesAsRead(chatId, currentUser.getId());
    }

    @DeleteMapping("/{chatId}")
    public void deleteChat(@PathVariable Long chatId) {
        chatService.delete(chatId);
    }
}
