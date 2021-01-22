package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.Chat;

import java.util.List;

/**
 * Spring Data репозиторий для взаимодействия с таблицей чатов.
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    /**
     * Метод для поиска списка чатов по идентификатору пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список чатов
     */
    List<Chat> findByUsersId(Long userId);
}
