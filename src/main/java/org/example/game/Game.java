package org.example.game;

import org.example.card.*;
import org.example.player.Player;
import org.example.player.PlayerRound;

import java.util.List;

public abstract class Game {
    protected List<Player> players;
    protected CardDeck deck;
    protected PlayerRound playerRound;

    // Constructor to initialize the players and deck
    public Game(List<Player> players) {
        this.players = players;
        this.deck = new CardDeck();
        this.playerRound = new PlayerRound();
        initializeGame();
    }

    // Initialize the game (e.g., deal cards, set up the deck)
    protected void initializeGame() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) { // Deal 7 cards to each player
                player.addCardToHand(deck.drawCardFromDeck());
            }
        }
        deck.discardCard(deck.drawCardFromDeck()); // Discard one card to start the discard pile
    }

    // Abstract methods to be implemented by concrete game classes
    public abstract void play();
    protected abstract void applyActionCard(ActionCard actionCard);
    protected abstract void applyWildCard(WildCard wildCard);
    protected abstract void handlePlayerTurn(Player player);
    protected abstract boolean isGameOver();
    protected abstract boolean playerWon(Player player);

    // Main game loop
    protected void gameLoop() {
        while (!isGameOver()) {
            Player currentPlayer = playerRound.getplayer();
            handlePlayerTurn(currentPlayer);
        }
    }

    // Advance to the next player (concrete method, can be used by all subclasses)
    protected void advanceToNextPlayer() {
        playerRound.getNextPlayer();
    }
}
