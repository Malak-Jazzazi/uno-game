package org.example.strategy;

import org.example.card.Card;
import org.example.card.CardColor;
import org.example.card.CardType;
import org.example.card.WildCard;
import org.example.game.Game;
import org.example.player.Player;

public class WildCardStrategy implements CardPlayStrategy {
    @Override
    public void playCard(Game game, Player player, Card card) {
        WildCard wildCard = (WildCard) card;
        CardColor chosenColor = player.getPlayerCards().chooseColorForWildCard();
        System.out.println("Player " + player.getId() + " (" + player.getName() + ") has played " + card.getCardType());
        System.out.println("Player " + player.getId() + " (" + player.getName() + ") changed the color to " + chosenColor);
        game.getDeck().setDeckColor(chosenColor);

        if (wildCard.getCardType() == CardType.WILD_DRAW_FOUR) {
            game.advanceToNextPlayer();
            Player nextPlayer = game.getPlayerRound().getCurrentPlayer();
            System.out.println("Player " + nextPlayer.getId() + " (" + nextPlayer.getName() + ") draws four cards.");
            nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
            nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
            nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
            nextPlayer.addCardToHand(game.getDeck().drawCardFromDeck());
        }
    }
}
