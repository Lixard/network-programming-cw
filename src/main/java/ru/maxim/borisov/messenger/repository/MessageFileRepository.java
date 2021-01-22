package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.MessageFile;

/**
 * Spring data репозиторий для взаимодействия с таблицей файлов сообщений.
 */
public interface MessageFileRepository extends JpaRepository<MessageFile, Long> {
}
