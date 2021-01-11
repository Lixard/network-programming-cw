package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxim.borisov.messenger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
