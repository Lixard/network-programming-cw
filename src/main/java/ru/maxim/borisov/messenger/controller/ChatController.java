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

/**
 * Контроллер для взаимодействия с чатами.
 */
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
                          CurrentUser currentUser,
                          SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.currentUser = currentUser;
    }

    /**
     * Создание чата.
     *
     * @param chatCreateDto данные для создания чата
     * @return зарегистрированный в системе чат
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGetDto create(@RequestBody ChatCreateDto chatCreateDto) {
        chatCreateDto.setCreatedBy(currentUser.getId());
        return chatService.create(chatCreateDto);
    }

    /**
     * Получение чатов текущего (авторизованного) пользователя.
     *
     * @return список чатов
     */
    @GetMapping
    public List<ChatGetDto> getCurrentUserChats() {
        return chatService.getCurrentUserChats(currentUser.getId());
    }

    /**
     * Энд-поинт для обновления текущего чата.
     *
     * @param chatId        идентификатор чата
     * @param chatUpdateDto обновленная модель чата
     * @return зарегистрированный в системе чат
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGetDto update(Long chatId, @RequestBody ChatUpdateDto chatUpdateDto) {
        chatUpdateDto.setId(chatId);
        return chatService.update(chatUpdateDto);
    }

    /**
     * Получение количества непрочитанных сообщений текущим пользователем в определенном чате.
     *
     * @param chatId идентификатор чата
     * @return количество непрочитанных сообщений (число)
     */
    @GetMapping("/{chatId}/unread")
    public Long getCurrentUserChatUnreadMessagesCount(@PathVariable Long chatId) {
        return messageService.getChatUnreadMessagesCountByCurrentUser(chatId, currentUser.getId());
    }

    /**
     * Получение всех сообщений определенного чата.
     *
     * @param chatId идентификатор чата
     * @return список всех сообщений
     */
    @GetMapping("/{chatId}/messages")
    public List<MessageGetDto> getAllMessages(@PathVariable Long chatId) {
        return messageService.getAllMessages(chatId);
    }

    /**
     * Энд-поинт для пометки всех сообщений чата для текущего (залогиненного) пользователя как прочитанных.
     *
     * @param chatId идентификатор чата
     */
    @PostMapping("/{chatId}/mark-as-read")
    public void markChatMessagesAsRead(@PathVariable Long chatId) {
        messageService.markChatMessagesAsRead(chatId, currentUser.getId());
    }

    /**
     * Энд-поинт для удаления чата.
     *
     * @param chatId идентификатор чата
     */
    @DeleteMapping("/{chatId}")
    public void deleteChat(@PathVariable Long chatId) {
        chatService.delete(chatId);
    }
}
