package ru.maxim.borisov.messenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.service.MessageService;

@RestController
@RequestMapping(
        path = "/messages",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class MessageController {

    private final CurrentUser currentUser;
    private final MessageService messageService;

    @Autowired
    public MessageController(CurrentUser currentUser, MessageService messageService) {
        this.currentUser = currentUser;
        this.messageService = messageService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageGetDto sendMessage(@RequestBody MessageCreateDto message) {
        message.setSenderId(currentUser.getId());
        return messageService.sendMessage(message);
    }
}
