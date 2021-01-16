package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.entity.Message;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper {

    @Mapping(target = "sender", source = "user")
    MessageGetDto toGetDto(Message message);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "sendDate", ignore = true)
    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "chat", ignore = true)
    Message fromGetDto(MessageGetDto messageGetDto);

    List<MessageGetDto> toGetDto(List<Message> messages);
}
