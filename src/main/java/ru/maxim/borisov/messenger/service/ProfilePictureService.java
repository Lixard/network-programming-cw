package ru.maxim.borisov.messenger.service;

import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;

public interface ProfilePictureService {
    ProfilePictureGetDto setPictureToUserProfile(Long userId, MultipartFile file);

    ProfilePictureDownloadGetDto downloadProfilePicture(Long userId);
}
