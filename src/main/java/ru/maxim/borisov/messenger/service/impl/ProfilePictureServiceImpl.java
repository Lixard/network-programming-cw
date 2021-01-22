package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;
import ru.maxim.borisov.messenger.mapper.ProfilePictureMapper;
import ru.maxim.borisov.messenger.repository.ProfilePictureRepository;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.service.ProfilePictureService;

/**
 * {@inheritDoc}
 */
@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {

    private final UserRepository userRepository;
    private final ProfilePictureRepository pictureRepository;
    private final ProfilePictureMapper profilePictureMapper;

    @Autowired
    public ProfilePictureServiceImpl(UserRepository userRepository,
                                     ProfilePictureRepository pictureRepository,
                                     ProfilePictureMapper profilePictureMapper) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.profilePictureMapper = profilePictureMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfilePictureGetDto setPictureToUserProfile(Long userId, MultipartFile file) {
        final var user = userRepository.findById(userId).orElseThrow();
        final var profilePicture = profilePictureMapper.toEntity(userId, file);
        final var savedPicture = pictureRepository.save(profilePicture);
        user.setProfilePicture(savedPicture);
        userRepository.save(user);
        return profilePictureMapper.toGetDto(savedPicture);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfilePictureDownloadGetDto downloadProfilePicture(Long userId) {
        final var user = userRepository.findById(userId).orElseThrow();
        final var profilePicture = user.getProfilePicture();
        return profilePictureMapper.toDownloadGetDto(profilePicture);
    }
}
