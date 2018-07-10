package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Team;
import com.example.postgresdemo.model.TeamUserMembership;
import com.example.postgresdemo.model.Story;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.model.Card;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.example.postgresdemo.repository.TeamUserMembershipRepository;
import com.example.postgresdemo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TeamUserMembershipController {

    @Autowired
    private TeamUserMembershipRepository teamUserMembershipRepository;

    @Autowired
    private TeamRepository teamRepository;

    /*
    //TODO
    public TeamUserMembership addNewMember(@PathVariable Long teamId,
                                                    @PathVariable Long userId
                                                    @Valid @RequestBody Team teamRequest) {

    }
    */
    @PostMapping("/teamUserMemberships")
    public TeamUserMembership createTeamUserMembership(@Valid @RequestBody TeamUserMembership teamUserMembership) {
        return teamUserMembershipRepository.save(teamUserMembership);
    }



    @PutMapping("/teamUserMemberships/{teamId}") 
    public TeamUserMembership setStatusToAdmin(@PathVariable Long teamId,
                                   @Valid @RequestBody TeamUserMembership teamUserMembershipRequest) {
        return teamUserMembershipRepository.findById(teamId)
                .map(teamUserMembership -> { //all these are passing changes on model back to repository
                    teamUserMembership.setStatus("Admin");
                    return teamUserMembershipRepository.save(teamUserMembership);
                }).orElseThrow(() -> new ResourceNotFoundException("TeamUserMembership not found with id " + teamId));
    }

    /*
    @PutMapping("/teamUserMemberships/{teamId}") 
    public TeamUserMembership setStatusToMember(@PathVariable Long teamId,
                                   @Valid @RequestBody TeamUserMembership teamUserMembershipRequest) {
        return teamUserMembershipRepository.findById(teamId)
                .map(teamUserMembership -> { //all these are passing changes on model back to repository
                    teamUserMembership.setStatus("Member");
                    return teamUserMembershipRepository.save(teamUserMembership);
                }).orElseThrow(() -> new ResourceNotFoundException("TeamUserMembership not found with id " + teamId));
    }   
    */
    
    /*

    @PutMapping("/teams/{teamId}") 
    public Team updateTeam(@PathVariable Long teamId,
                                   @Valid @RequestBody Team teamRequest) {
        return teamRepository.findById(teamId)
                .map(team -> { //all these are passing changes on model back to repository
                    team.setTeamName(teamRequest.getTeamName());
                    team.setType(teamRequest.getType());
                    return teamRepository.save(team);
                }).orElseThrow(() -> new ResourceNotFoundException("Team not found with id " + teamId));
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
        return teamRepository.findById(teamId)
                .map(team -> {
                    teamRepository.delete(team);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Team not found with id " + teamId));
    }
    */
}
