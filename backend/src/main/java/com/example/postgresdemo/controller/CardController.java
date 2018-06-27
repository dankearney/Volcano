package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Card;
import com.example.postgresdemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards") //name of the card table
    public Page<Card> getCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }


    @PostMapping("/card")
    public Card createCard(@Valid @RequestBody Card card) {
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
