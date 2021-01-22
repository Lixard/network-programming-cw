package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.maxim.borisov.messenger.dto.create.ChatCreateDto;
import ru.maxim.borisov.messenger.dto.get.ChatGetDto;
import ru.maxim.borisov.messenger.entity.Chat;

import java.util.List;

/**
 * Преобразователь моделей, относящихся к сущности чата.
 */
@Mapper(componentModel = "spring")
public interface ChatMapper {

    /**
     * Преобразование сущности в ChatGetDto.
     *
     * @param chat сущность
     * @return ChatGetDto
     */
    @Mapping(target = "participations", source = "users")
    ChatGetDto toGetDto(Chat chat);

    /**
     * Преобразование из ChatGetDto в сущность.
     *
     * @param chatGetDto data transfer object
     * @return сущность чата
     */
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Chat fromGetDto(ChatGetDto chatGetDto);

    /**
     * Преобразование списка сущностей в список dto.
     *
     * @param chats список сущностей
     * @return список dto
     */
    List<ChatGetDto> toGetDto(List<Chat> chats);

    /**
     * Преобразование из ChatCreateDto в сущность чата.
     *
     * @param chatCreateDto data transfer object для создания чата.
     * @return список сущностей чата
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdBy.id", source = "createdBy")
    Chat fromCreateDto(ChatCreateDto chatCreateDto);
}
