package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.NotificationGetDto;
import ru.maxim.borisov.messenger.mapper.NotificationMapper;
import ru.maxim.borisov.messenger.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationGetDto createNotification(MessageCreateDto message) {
        return notificationMapper.fromMessageCreateDto(message);
    }
}
