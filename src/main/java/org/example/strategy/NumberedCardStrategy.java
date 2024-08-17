package org.example.strategy;

import org.example.card.Card;
import org.example.card.NumberedCard;
import org.example.game.Game;
import org.example.player.Player;

public class NumberedCardStrategy implements CardPlayStrategy {
    @Override
    public void playCard(Game game, Player player, Card card) {
        NumberedCard numberedCard = (NumberedCard) card;
        System.out.println("Player " + player.getId() + " (" + player.getName() + ") played " +
                numberedCard.getCardColor() + " " + numberedCard.getValue());
    }
}
