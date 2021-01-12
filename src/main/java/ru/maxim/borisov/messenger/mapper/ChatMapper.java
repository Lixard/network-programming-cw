package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import ru.maxim.borisov.messenger.dto.get.ChatWithLastUnreadMessageGetDto;
import ru.maxim.borisov.messenger.entity.Chat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatWithLastUnreadMessageGetDto toChatsWithLastUnreadMessageGetDto(Chat chat);

    List<ChatWithLastUnreadMessageGetDto> toChatsWithLastUnreadMessageGetDto(List<Chat> chats);
}
