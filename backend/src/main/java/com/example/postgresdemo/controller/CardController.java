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

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired 
    private UserRepository userRepository;

    @GetMapping("/cards") //name of the card table
    public List<Card> getCards() {
        List<Card> cards = cardRepository.findAll();

        // Hydrate the assignee, creator with actual User objects.
        // TODO: Make this more efficient -- this does two user table calls per card!
        // Ideally let's do this in the Model layer itself. 
        // Or at the very worst, we'll do one batch call to the User table.
        for ( Card card : cards ) {
            card.setCreator(userRepository.findOneByUserid(card.getCreatorId()));
            card.setAssignee(userRepository.findOneByUserid(card.getAssigneeId()));
        }
        return cards;
    }

    @PostMapping("/card")
    public Card createCard(@Valid @RequestBody Card card) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = ((VolcanoUserPrincipal)principal).getUser().getUserid();
        card.setCreatorId(userId);
        return cardRepository.save(card);
    }

    @PutMapping("/cards/{cardId}")
    public Card update(@PathVariable Long cardId,
                                   @Valid @RequestBody Card cardRequest) {
        return cardRepository.findById(cardId)
                .map(card -> {
                    card.setCardName(cardRequest.getCardName());
                    card.setType(cardRequest.getType());
                    card.setPriority(cardRequest.getPriority());
                    card.setLabel(cardRequest.getLabel());
                    card.setStatus(cardRequest.getStatus());
                    card.setResolution(cardRequest.getResolution());
                    card.setDescription(cardRequest.getDescription());
                    card.setAttachment(cardRequest.getAttachment());
                    return cardRepository.save(card);
                }).orElseThrow(() -> new ResourceNotFoundException("Card not found with id " + cardId));
    }


    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId) {
        return cardRepository.findById(cardId)
                .map(card -> {
                    cardRepository.delete(card);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Card not found with id " + cardId));
    }

}
