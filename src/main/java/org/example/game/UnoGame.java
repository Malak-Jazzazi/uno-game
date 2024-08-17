package org.example.game;

import org.example.card.*;
import org.example.player.Player;

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
            case REVERSE -> playerRound.reverseDirection();
            case SKIP -> playerRound.next();
            case DRAW_TWO -> {
                playerRound.next();
                Player nextPlayer = playerRound.getCurrentPlayer();
                nextPlayer.addCardToHand(deck.drawCardFromDeck());
                nextPlayer.addCardToHand(deck.drawCardFromDeck());
            }
            default -> {
            }
        }
    }

    @Override
    protected void applyWildCard(WildCard wildCard) {
        CardColor chosenColor;
        if (wildCard.getCardType() == CardType.WILD_DRAW_FOUR) {
            playerRound.next();
            Player nextPlayer = playerRound.getCurrentPlayer();
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            nextPlayer.addCardToHand(deck.drawCardFromDeck());
            playerRound.next();
        }
        chosenColor = playerRound.getCurrentPlayer().getPlayerCards().chooseColorForWildCard();
        deck.setDeckColor(chosenColor);
    }

    @Override
    protected void handlePlayerTurn(Player player) {
        Card topCard = deck.lastThrownCard();
        CardColor deckColor = deck.getDeckColor();

        Optional<Card> playedCard = player.getCardFromHand(topCard, deckColor);

        if (playedCard.isPresent()) {
            player.removeCardFromHand(playedCard.get());
            deck.discardCard(playedCard.get());

            if (playedCard.get() instanceof ActionCard) {
                applyActionCard((ActionCard) playedCard.get());
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") played " +
                        playedCard.get().getCardColor() + " " + playedCard.get().getCardType());
            } else if (playedCard.get() instanceof WildCard) {
                applyWildCard((WildCard) playedCard.get());
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") played " +
                        playedCard.get().getCardColor() + " " + playedCard.get().getCardType());
            } else if (playedCard.get() instanceof NumberedCard) {
                NumberedCard numberedCard = (NumberedCard) playedCard.get();
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") played " +
                        numberedCard.getCardColor() + " " + numberedCard.getValue());
            }

            if (playerWon(player)) {
                System.out.println("Player " + player.getId() + " (" + player.getName() + ") has won");
                return;
            }
        } else {
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
