package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Spring data репозиторий для взаимодействия с таблицей пользователей в базе данных.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Метод для поиска пользователя по имени (имена уникальны). Используется в Spring Security.
     *
     * @param username имя пользователя
     * @return волшебная коробка с пользователем либо его отсутствием (Optional)
     */
    Optional<User> findOneByUsername(String username);

    /**
     * Метод для получаения всех пользователей, участвующих в определенном чате.
     *
     * @param chatId идентификатор чата
     * @return список пользователей
     */
    List<User> findAllByChatsId(Long chatId);
}
