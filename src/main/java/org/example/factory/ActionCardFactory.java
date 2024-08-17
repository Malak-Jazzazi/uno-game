package org.example.factory;

import org.example.card.*;

public class ActionCardFactory implements CardFactory {

    @Override
    public Card createCard(CardColor color, CardType cardType, int value) {
        return new ActionCard(color,cardType);
    }
}
