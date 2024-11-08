package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private CardRepository cardRepository;
    @Autowired
    public CardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }
    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getCardsByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        CardValidation.validateCard(card);
        cardRepository.save(card);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/")
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        if (card.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        cardRepository.update(card);
        return ResponseEntity.ok(card);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        cardRepository.remove(id);
        return ResponseEntity.ok("Card successfully deleted");
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardsByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }
}
