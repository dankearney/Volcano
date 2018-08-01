package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Story;
import com.example.postgresdemo.model.Card;
import com.example.postgresdemo.model.User;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.HashSet;
import com.example.postgresdemo.security.VolcanoUserPrincipal;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.repository.StoryRepository;
import com.example.postgresdemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.*;
import org.springframework.context.annotation.*;

@RestController
@Transactional

public class StoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/stories")
    public List<Story> getStories() {
        List<Story> stories = storyRepository.findAll();
        return hydrateStories(stories);
    }

    @GetMapping("/stories/{storyId}") //name of the card table
    public Story getStory(@PathVariable Long storyId) {
        Story story = storyRepository.findByStoryId(storyId);
        hydrateStory(story);
        return story;
    }

    public void hydrateStory(Story story) {
      ArrayList<Card> cards = cardRepository.findByStoryId(story.getStoryId());
      story.setCreator(userRepository.findByUserid(story.getCreatorId()));
      story.setCardsAttached(hydrateCards(cards));
    }

    public List<Story> hydrateStories(List<Story> stories) {
        for (Story story: stories) {
          hydrateStory(story);
        }
        return stories;
    }

    // Returns Story scoped to a specific team
    @GetMapping("/team/{teamId}/stories")
    public List<Story> getTeamScopedStories(@PathVariable("teamId") Long teamId) {
        List<Story> stories = storyRepository.findByTeamId(teamId);
        return hydrateStories(stories);
    }

    @PostMapping("/team/{teamId}/stories")
    public Story createStoryInTeam(@Valid @RequestBody Story story, @PathVariable("teamId") Long teamId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        story.setCreatorId(userId);
        story.setTeamId(teamId);
        return storyRepository.save(story);
    }

    @PostMapping("/stories")
    public Story createStory(@Valid @RequestBody Story story) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        story.setCreatorId(userId);
        return storyRepository.save(story);
    }

    @PutMapping("/stories/{storyId}") //get story according to story primary key in TABLE
    public Story updateStory(@PathVariable Long storyId,
                                   @Valid @RequestBody Story storyRequest) {
        return storyRepository.findById(storyId)
                .map(story -> { //all these are passing changes on model back to repository
                    story.setStoryName(storyRequest.getStoryName());
                    story.setType(storyRequest.getType());
                    story.setPriority(storyRequest.getPriority());
                    story.setLabel(storyRequest.getLabel());
                    story.setStatus(storyRequest.getStatus());
                    story.setResolution(storyRequest.getResolution());
                    story.setDescription(storyRequest.getDescription());
                    story.setAttachment(storyRequest.getAttachment());
                    return storyRepository.save(story);
                }).orElseThrow(() -> new ResourceNotFoundException("Story not found with id " + storyId));
    }

    @DeleteMapping("/stories/{storyId}")
    public ResponseEntity<?> deleteStory(@PathVariable Long storyId) {
        return storyRepository.findById(storyId)
                .map(story -> {
                    storyRepository.delete(story);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Story not found with id " + storyId));
    }

    //helper function
    public ArrayList<Card> hydrateCards(ArrayList<Card> cards) {
        // Cache the user ID <=> user object to avoid lots of
        // Requests to the User DB.

        // Initialize it empty.
        HashMap<Long, User> userIdCache = new HashMap<Long, User>();
        HashSet<Long> userIds = new HashSet<Long>();

        for ( Card card : cards ) {
            userIds.add(card.getCreatorId());
            userIds.add(card.getAssigneeId());
        }
        userIds.remove(null);
        System.out.println(cards);
        System.out.println(new ArrayList(userIds));

        // Look up all the users in batch and fill the cache.
        List<User> users = userRepository.findByUseridIn(new ArrayList(userIds));
        for ( User user : users ) {
            userIdCache.put(user.getUserid(), user);
        }

        // Hydrate the assignee, creator with actual User objects from the cache.
        for ( Card card : cards ) {
            if ( card.getCreatorId() != null ) {
                card.setCreator( userIdCache.get(card.getCreatorId()  ) );
            }
            if ( card.getAssigneeId() != null ) {
               card.setAssignee(userIdCache.get(card.getAssigneeId() ) );
            }
        }

        for (Card card: cards) {
          card.setCreator(userRepository.findByUserid(card.getCreatorId()));
          card.setAssignee(userRepository.findByUserid(card.getAssigneeId()));
        }

        return cards;
    }
}
