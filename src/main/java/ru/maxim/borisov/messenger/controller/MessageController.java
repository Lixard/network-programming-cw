package ru.maxim.borisov.messenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.service.MessageService;
import ru.maxim.borisov.messenger.service.NotificationService;

@Controller
public class MessageController {

    private final CurrentUser currentUser;
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public MessageController(CurrentUser currentUser,
                             MessageService messageService,
                             SimpMessagingTemplate simpMessagingTemplate,
                             NotificationService notificationService) {
        this.currentUser = currentUser;
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
    }

    @MessageMapping("/messages")
    public void processMessages(@Payload MessageCreateDto message) {
        message.setSenderId(currentUser.getId());
        final var messageGetDto = messageService.sendMessage(message);
        simpMessagingTemplate.convertAndSend("/chats/" + message.getChatId(), messageGetDto);

        final var messageNotification = notificationService.createNotification(message);
        simpMessagingTemplate.convertAndSend("/notifications/chats/messages", messageNotification);
    }
}
