package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.ProfilePicture;

/**
 * Spring data репозиторий для взаимодействия с таблицей картинок пользователя.
 */
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {
}
