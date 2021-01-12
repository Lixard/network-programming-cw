package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.entity.Chat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(target = "participations", source = "users")
    ChatGetDto toGetDto(Chat chat);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Chat fromGetDto(ChatGetDto chatGetDto);

    List<ChatGetDto> toGetDto(List<Chat> chats);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdBy.id", source = "createdBy")
    Chat fromCreateDto(ChatCreateDto chatCreateDto);
}
