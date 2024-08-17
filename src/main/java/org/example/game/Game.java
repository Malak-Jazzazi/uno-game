package org.example.game;

import org.example.card.*;
import org.example.player.Player;
import org.example.player.PlayerRound;
import org.example.strategy.ActionCardStrategy;
import org.example.strategy.CardPlayStrategy;
import org.example.strategy.NumberedCardStrategy;
import org.example.strategy.WildCardStrategy;

import java.util.List;
import java.util.Map;

public abstract class Game {
    protected List<Player> players;
    protected CardDeck deck;
    protected PlayerRound playerRound;
    protected Map<Class<? extends Card>, CardPlayStrategy> cardStrategies;
    public Game(List<Player> players) {
        this.players = players;
        this.deck = CardDeck.getInstance();
        this.playerRound = PlayerRound.getInstance(players);
        cardStrategies = Map.of(
                NumberedCard.class, new NumberedCardStrategy(),
                ActionCard.class, new ActionCardStrategy(),
                WildCard.class, new WildCardStrategy()
        );
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

    public void advanceToNextPlayer() {
        playerRound.advanceToNextPlayer();
    }

    public PlayerRound getPlayerRound() {
        return playerRound;
    }

    public void setPlayerRound(PlayerRound playerRound) {
        this.playerRound = playerRound;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

}
