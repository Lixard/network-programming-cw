package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.NotificationGetDto;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationGetDto fromMessageCreateDto(MessageCreateDto message);
}
