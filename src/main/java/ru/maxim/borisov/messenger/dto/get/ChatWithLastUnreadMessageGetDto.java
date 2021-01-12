package ru.maxim.borisov.messenger.dto.get;

public class ChatWithLastUnreadMessageGetDto {

    private ChatGetDto chat;
    private MessageGetDto lastMessage;
    private Long unreadMessagesCount;

    public ChatGetDto getChat() {
        return chat;
    }

    public void setChat(ChatGetDto chat) {
        this.chat = chat;
    }

    public MessageGetDto getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageGetDto lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getUnreadMessagesCount() {
        return unreadMessagesCount;
    }

    public void setUnreadMessagesCount(Long unreadMessagesCount) {
        this.unreadMessagesCount = unreadMessagesCount;
    }
}
