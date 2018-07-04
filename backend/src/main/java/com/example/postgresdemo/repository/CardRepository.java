package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardName(String cardName);

    ArrayList<Card> findByStoryId(Long storyId);
}
