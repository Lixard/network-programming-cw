package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.entity.Message;

import java.time.Instant;
import java.util.List;


/**
 * Преобразователь моделей, относящихся к сущности сообщений.
 */
@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, ChatMapper.class},
        imports = {Instant.class}
)
public interface MessageMapper {

    /**
     * Преобразование сущности сообщения в dto.
     *
     * @param message сущность сообщения
     * @return dto
     */
    @Mapping(target = "files", source = "messageFiles")
    @Mapping(target = "sender", source = "user")
    MessageGetDto toGetDto(Message message);

    /**
     * Создание сущности из dto.
     *
     * @param message dto
     * @return сущность сообщения
     */
    @Mapping(target = "messageFiles", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "chat", ignore = true)
    @Mapping(target = "messageStatuses", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sendDate", expression = "java(Instant.now())")
    @Mapping(target = "chat.id", source = "chatId")
    @Mapping(target = "user.id", source = "senderId")
    Message fromCreateDto(MessageCreateDto message);

    /**
     * Преобраование списка сущностей сообщений в список dto.
     *
     * @param messages список сущностей сообщений
     * @return список dto
     */
    List<MessageGetDto> toGetDto(List<Message> messages);
}
