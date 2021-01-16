package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;
import ru.maxim.borisov.messenger.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetDto toGetDto(User user);

    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "id", ignore = true)
    User fromCreateDto(UserCreateDto userCreateDto);

    List<UserGetDto> toGetDto(List<User> users);
}
