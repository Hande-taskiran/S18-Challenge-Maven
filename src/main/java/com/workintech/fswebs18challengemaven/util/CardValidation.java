package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validateCard(Card card) {
        if (card == null) {
            throw new CardException("Card cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (card.getValue() != null && card.getType() != null) {
            throw new CardException("Card cannot have both value and type", HttpStatus.BAD_REQUEST);
        }
        if ("JOKER".equals(card.getType())) {
            if (card.getValue() != null || card.getColor() != null) {
                throw new CardException("JOKER card cannot have value or color", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
