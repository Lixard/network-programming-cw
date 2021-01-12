package ru.maxim.borisov.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.security.model.LoginSuccessModel;

@RestController
@RequestMapping(
        path = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthController {

    private final CurrentUser currentUser;

    @Autowired
    public AuthController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping(path = "/this")
    public LoginSuccessModel getCurrentUser() {
        return new LoginSuccessModel(currentUser);
    }
}
