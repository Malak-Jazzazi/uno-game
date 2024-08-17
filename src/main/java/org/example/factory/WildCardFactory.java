package org.example.factory;

import org.example.card.*;

public class WildCardFactory implements CardFactory {

    @Override
    public Card createCard(CardColor color, CardType cardType, int value) {
        return new WildCard(cardType);
    }
}
