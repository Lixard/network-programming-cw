package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;
import ru.maxim.borisov.messenger.entity.User;

import java.util.List;

/**
 * Преобразователь моделей, относящихся к сущности пользователя.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразование сущности в dto.
     *
     * @param user сущность
     * @return dto
     */
    @Mapping(target = "picture", source = "profilePicture")
    UserGetDto toGetDto(User user);

    /**
     * Создание сущности базы данных из dto.
     *
     * @param userCreateDto dto для создания
     * @return сущность пользователя
     */
    @Mapping(target = "profilePicture", ignore = true)
    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "id", ignore = true)
    User fromCreateDto(UserCreateDto userCreateDto);

    /**
     * Преобразование списка сущностей в список dto.
     *
     * @param users список сущностей
     * @return список dto
     */
    List<UserGetDto> toGetDto(List<User> users);
}
