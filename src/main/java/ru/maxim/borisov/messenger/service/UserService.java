package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;

import java.util.List;

/**
 * Сервис, отвечающий за взаимодействие с сущностью пользователя.
 */
public interface UserService {
    /**
     * Метод для создания пользователя.
     *
     * @param userCreateDto - пользователь для создания
     * @return Сохраненный пользователь
     */
    UserGetDto create(UserCreateDto userCreateDto);

    /**
     * Получение всех пользователей.
     *
     * @return Список всех пользователей
     */
    List<UserGetDto> getAll();

    UserGetDto getById(long userId);
}
