package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
