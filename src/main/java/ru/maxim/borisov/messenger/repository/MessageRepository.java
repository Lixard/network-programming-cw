package ru.maxim.borisov.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maxim.borisov.messenger.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select count(m) "
            + "from MessageStatus ms "
            + "join Message m "
            + "where ms.isRead = false and m.chat.id in :chatIds and ms.user.id = :userId "
            + "order by m.sendDate desc")
    List<Long> countAllWhereUnread(List<Long> chatIds, Long userId);

}
