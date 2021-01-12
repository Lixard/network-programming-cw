package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;

public interface UserService {
    UserGetDto create(UserCreateDto userCreateDto);
}
