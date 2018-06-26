package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Story findByStoryname(String storyname);
}
