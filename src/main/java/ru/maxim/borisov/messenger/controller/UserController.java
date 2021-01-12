package ru.maxim.borisov.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatWithLastUnreadMessageGetDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;
import ru.maxim.borisov.messenger.service.ChatService;
import ru.maxim.borisov.messenger.service.UserService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public UserGetDto create(@RequestBody UserCreateDto user) {
        return userService.create(user);
    }

    @GetMapping(path = "/{userId}/chats")
    public List<ChatWithLastUnreadMessageGetDto> getChatsWithLastUnreadMessageByUserId(@PathVariable Long userId) {
        return null;
    }
}
