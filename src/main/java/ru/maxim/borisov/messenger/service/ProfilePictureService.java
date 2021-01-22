package ru.maxim.borisov.messenger.service;

import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;

/**
 * Сервис, отвечающий за взаимодействие с аватарками пользователя.
 */
public interface ProfilePictureService {
    /**
     * Метод для регистрации аватарки в системе
     *
     * @param userId идентификатор пользователя
     * @param file   файл (аватарка)
     * @return мета-информация об аватарке
     */
    ProfilePictureGetDto setPictureToUserProfile(Long userId, MultipartFile file);

    /**
     * Метод для выгрузки картинки из системы
     *
     * @param userId идентификатор пользователя
     * @return аватарка пользователя
     */
    ProfilePictureDownloadGetDto downloadProfilePicture(Long userId);
}
