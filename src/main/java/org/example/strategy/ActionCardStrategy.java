package org.example.strategy;

import org.example.card.ActionCard;
import org.example.card.Card;
import org.example.game.Game;
import org.example.player.Player;

public class ActionCardStrategy implements CardPlayStrategy {
    @Override
    public void playCard(Game game, Player player, Card card) {
        ActionCard actionCard = (ActionCard) card;
        System.out.println("Player " + player.getId() + " (" + player.getName() + ") played " +
                actionCard.getCardType() + " " + actionCard.getCardColor());
        switch (actionCard.getCardType()) {
            case REVERSE -> {
                game.getPlayerRound().reverseDirection();
                System.out.println("Play direction reversed.");
            }
            case SKIP -> {
                game.advanceToNextPlayer();
                System.out.println("Player " + game.getPlayerRound().getCurrentPlayer().getId() + " (" +
                        game.getPlayerRound().getCurrentPlayer().getName() + ") is skipped.");
            }
            case DRAW_TWO -> {
                game.advanceToNextPlayer();
                Player nextPlayer = game.getPlayerRound().getCurrentPlayer();
                System.out.println("Player " + nextPlayer.getId() + " (" + nextPlayer.getName() + ") draws two cards.");
                nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
                nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
            }
        }
    }
}
