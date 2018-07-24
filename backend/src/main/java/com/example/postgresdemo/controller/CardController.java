package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Card;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.CardRepository;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.security.VolcanoUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.util.Optional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cards/{cardId}")
    public Card getCard(@PathVariable Long cardId) {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(cardRepository.findById(cardId).get());
        return hydrateCards(cards).get(0);
    }

    public List<Card> hydrateCards(List<Card> cards) {
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

    @GetMapping("/cards")
    public List<Card> getCards() {
        List<Card> cards = cardRepository.findAll();
        return hydrateCards(cards);
    }

    // Returns cards scoped to a specific team
    @GetMapping("/team/{teamId}/cards")
    public List<Card> getTeamScopedCards(@PathVariable("teamId") Long teamId) {
        List<Card> cards = cardRepository.findByTeamId(teamId);
        return hydrateCards(cards);
    }

    @PostMapping("/cards")
    public Card createCard(@Valid @RequestBody Card card) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        card.setCreatorId(userId);
        return cardRepository.save(card);
    }

    @PostMapping("/team/{teamId}/cards")
    public Card createCardInTeam(@Valid @RequestBody Card card, @PathVariable("teamId") Long teamId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        card.setCreatorId(userId);
        card.setTeamId(teamId);
        return cardRepository.save(card);
    }

    @PutMapping("/cards/{cardId}")
    public Card update(@PathVariable Long cardId,  @Valid @RequestBody Card cardRequest) {
        Card card = cardRepository.findById(cardId).get();
        card.setCardName(cardRequest.getCardName());
        card.setType(cardRequest.getType());
        card.setPriority(cardRequest.getPriority());
        card.setLabel(cardRequest.getLabel());
        card.setStatus(cardRequest.getStatus());
        card.setResolution(cardRequest.getResolution());
        card.setDescription(cardRequest.getDescription());
        card.setAttachment(cardRequest.getAttachment());
        card.setAssigneeId(cardRequest.getAssigneeId());
        return cardRepository.save(card);
    }

    /*
    @PutMapping("/cards/{cardId}")
    public Card markAsComplete(@PathVariable Long cardId,
                                   @Valid @RequestBody Card cardRequest) {
        return cardRepository.findById(cardId)
                .map(card -> {
                    card.setResolution("Complete");
                    return cardRepository.save(card);
                }).orElseThrow(() -> new ResourceNotFoundException("Card not found with id " + cardId));
    }
    */

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId) {
        return cardRepository.findById(cardId)
                .map(card -> {
                    cardRepository.delete(card);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Card not found with id " + cardId));
    }

}
