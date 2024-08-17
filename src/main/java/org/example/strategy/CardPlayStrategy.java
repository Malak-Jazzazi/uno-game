package org.example.strategy;

import org.example.card.Card;
import org.example.game.Game;
import org.example.player.Player;

public interface CardPlayStrategy {
    void playCard(Game game, Player player, Card card);
}
