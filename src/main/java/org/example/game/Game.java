package org.example.game;

import org.example.card.*;
import org.example.player.Player;
import org.example.player.PlayerRound;

import java.util.List;

public abstract class Game {
    protected List<Player> players;
    protected CardDeck deck;
    protected PlayerRound playerRound;

    public Game(List<Player> players) {
        this.players = players;
        this.deck = CardDeck.getInstance();
        this.playerRound = PlayerRound.getInstance(players);
        initializeGame();
    }

    protected void initializeGame() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCardToHand(deck.drawCardFromDeck());
            }
        }
        deck.discardCard(deck.drawCardFromDeck());
    }

    public abstract void play();
    protected abstract void applyActionCard(ActionCard actionCard);
    protected abstract void applyWildCard(WildCard wildCard);
    protected abstract void handlePlayerTurn(Player player);
    protected abstract boolean isGameOver();
    protected abstract boolean playerWon(Player player);

    // Main game loop
    protected void gameLoop() {
        while (!isGameOver()) {
            Player currentPlayer = playerRound.getCurrentPlayer();
            handlePlayerTurn(currentPlayer);
        }
    }

    protected void advanceToNextPlayer() {
        playerRound.next();
    }
}
