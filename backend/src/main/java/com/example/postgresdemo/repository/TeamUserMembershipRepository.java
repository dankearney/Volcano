package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.TeamUserMembership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface TeamUserMembershipRepository extends JpaRepository<TeamUserMembership, Long> {

    ArrayList<TeamUserMembership> findByTeamId(Long teamId);
    ArrayList<TeamUserMembership> findByUserId(long userId);
    Long deleteByTeamIdAndUserId(Long userId, Long teamId);
    TeamUserMembership findOneByTeamIdAndUserId(Long userId, Long teamId);
}
