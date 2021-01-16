package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;

import java.util.List;

public interface UserService {
    UserGetDto create(UserCreateDto userCreateDto);

    List<UserGetDto> getAll();
}
