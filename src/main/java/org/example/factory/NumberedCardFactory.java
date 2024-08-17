package org.example.factory;

import org.example.card.Card;
import org.example.card.CardColor;
import org.example.card.CardType;
import org.example.card.NumberedCard;

public class NumberedCardFactory implements CardFactory {

    @Override
    public Card createCard(CardColor color, CardType cardType , int value) {
        return new NumberedCard(color, value);
    }
}
