package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Team;
import com.example.postgresdemo.model.Story;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.model.Card;
import com.example.postgresdemo.model.TeamUserMembership;
import java.util.List;
import java.util.ArrayList;
import com.example.postgresdemo.security.VolcanoUserPrincipal;
import java.util.Optional;
import com.example.postgresdemo.repository.TeamRepository;
import com.example.postgresdemo.repository.TeamUserMembershipRepository;
import com.example.postgresdemo.repository.StoryRepository;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private TeamUserMembershipRepository tumRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/teams") //mapped to a table named teams in database
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    //returns a team with stories attached
    @GetMapping("/teams/{teamId}")
    public Team getTeam(@PathVariable Long teamId) {
        Team team = teamRepository.findByTeamId(teamId);
        ArrayList<Story> stories = storyRepository.findByTeamId(teamId);
        team.setStoriesAttached(stories);

        ArrayList<TeamUserMembership> tum = tumRepository.findByTeamId(teamId);
        ArrayList<User> users = new ArrayList<User>();

        for (TeamUserMembership t: tum) {
          users.add(userRepository.findByUserid(t.getUserId()));
        }

        team.setUsersInTeam(users);
        return team;
    }

    // Returns the teams I'm a member of
    @GetMapping("/myTeams")
    public ArrayList<Team> getMyTeams() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        ArrayList<TeamUserMembership> memberships = tumRepository.findByUserId(userId);

        ArrayList<Long> teamIds = new ArrayList<Long>();
        for ( TeamUserMembership tum : memberships ) {
            teamIds.add(tum.getTeamId());
        }
        return teamRepository.findByTeamIdIn(teamIds);
    }

    @PostMapping("/teams")
    public Team createTeam(@Valid @RequestBody Team team) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        team.setCreatorId(userId);
        team = teamRepository.save(team);

        // Add yourself to the team membership table
        TeamUserMembership tum = new TeamUserMembership();
        tum.setTeamId(team.getTeamId());
        tum.setUserId(userId);
        tum.setStatus("Admin");
        tumRepository.save(tum);

        return team;
    }

    @PutMapping("/teams/{teamId}")
    public Team updateTeam(@PathVariable Long teamId,
                                   @Valid @RequestBody Team teamRequest) {
        return teamRepository.findById(teamId)
                .map(team -> { //all these are passing changes on model back to repository
                    team.setTeamName(teamRequest.getTeamName());
                    team.setPassword(teamRequest.getPassword()); //to allow password change
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
}
