package org.example.game;

import org.example.card.*;
import org.example.player.Player;
import org.example.strategy.CardPlayStrategy;

import java.util.List;
import java.util.Optional;

public class UnoGame extends Game {

    public UnoGame(List<Player> players) {
        super(players);
    }

    @Override
    public void play() {
        gameLoop();
    }

    @Override
    protected void applyActionCard(ActionCard actionCard) {
        switch (actionCard.getCardType()) {
            case REVERSE -> {
                playerRound.reverseDirection();
                System.out.println("Play direction reversed.");
            }
            case SKIP -> {
                advanceToNextPlayer();
                System.out.println("Player " + playerRound.getCurrentPlayer().getId() + " (" + playerRound.getCurrentPlayer().getName() + ") is skipped.");
            }
            case DRAW_TWO -> {
                advanceToNextPlayer();
                Player nextPlayer = playerRound.getCurrentPlayer();
                System.out.println("Player " + nextPlayer.getId() + " (" + nextPlayer.getName() + ") draws two cards.");
                nextPlayer.addCardToHand(deck.drawCardFromDeck());
                nextPlayer.addCardToHand(deck.drawCardFromDeck());
            }
            default -> {
            }
        }
    }

    @Override
    protected void applyWildCard(WildCard wildCard) {
        CardColor chosenColor = playerRound.getCurrentPlayer().getPlayerCards().chooseColorForWildCard();
        System.out.println("Player " + playerRound.getCurrentPlayer().getId() + " (" + playerRound.getCurrentPlayer().getName() + ") changed the color to " + chosenColor);
        if (wildCard.getCardType() == CardType.WILD_DRAW_FOUR) {
            advanceToNextPlayer();
            Player nextPlayer = playerRound.getCurrentPlayer();
            System.out.println("Player " + nextPlayer.getId() + " (" + nextPlayer.getName() + ") draws four cards.");
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
        }
        deck.setDeckColor(chosenColor);
    }

    @Override
    protected void handlePlayerTurn(Player player) {
        System.out.println("Player " + player.getId() + " (" + player.getName() + ")'s turn.");

        Card topCard = deck.lastThrownCard();
        CardColor deckColor = deck.getDeckColor();

        Optional<Card> playedCard = player.getCardFromHand(topCard, deckColor);

        if (playedCard.isPresent()) {
            player.removeCardFromHand(playedCard.get());
            deck.discardCard(playedCard.get());

            Card card = playedCard.get();
            CardPlayStrategy strategy = cardStrategies.get(card.getClass());
            strategy.playCard(this, player, card);

            if (playerWon(player)) {
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") has won the game!");
                return;
            }
        } else {
            System.out.println("Player " + player.getId() + " (" + player.getName() + ") cannot play and draws a card.");
            player.addCardToHand(deck.drawCardFromDeck());
        }
        advanceToNextPlayer();
    }

    @Override
    protected boolean isGameOver() {
        for (Player player : players) {
            if (playerWon(player)) {
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") has won");
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean playerWon(Player player) {
        return player.getPlayerCards().getCards().isEmpty();
    }
}
