package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAll();

    List<Chat> findByTeamIdOrderByChatId(Long teamId);
    //List<Chat> findByChatId(Long chatId);
}
