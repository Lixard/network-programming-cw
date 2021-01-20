package ru.maxim.borisov.messenger.service;

import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.NotificationGetDto;

public interface NotificationService {
    NotificationGetDto createNotification(MessageCreateDto message);
}
