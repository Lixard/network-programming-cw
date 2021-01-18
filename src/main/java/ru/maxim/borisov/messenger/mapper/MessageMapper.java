package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.entity.Message;

import java.time.Instant;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, ChatMapper.class},
        imports = {Instant.class}
)
public interface MessageMapper {

    @Mapping(target = "sender", source = "user")
    MessageGetDto toGetDto(Message message);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "chat", ignore = true)
    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sendDate", expression = "java(Instant.now())")
    @Mapping(target = "chat.id", source = "chatId")
    @Mapping(target = "user.id", source = "senderId")
    Message fromCreateDto(MessageCreateDto message);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "sendDate", ignore = true)
    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "chat", ignore = true)
    Message fromGetDto(MessageGetDto messageGetDto);

    List<MessageGetDto> toGetDto(List<Message> messages);
}
