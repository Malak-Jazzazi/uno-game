package org.example.factory;

import org.example.card.Card;
import org.example.card.CardColor;
import org.example.card.CardType;

public interface CardFactory {
    Card createCard(CardColor color, CardType cardType, int value);
}
