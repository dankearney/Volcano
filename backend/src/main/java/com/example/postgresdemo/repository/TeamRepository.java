package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamId(Long teamId);
}
