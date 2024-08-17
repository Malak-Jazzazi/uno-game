package org.example.game;

import org.example.card.CardDeck;
import org.example.player.Player;
import org.example.player.PlayerRound;

import java.util.List;

public abstract class Game2 {
    protected List<Player> players;
    protected CardDeck cardDeck;
    protected PlayerRound playerRound;

    public Game2(List<Player> players) {
        this.players = players;
        this.cardDeck = CardDeck.getInstance();
        this.playerRound = PlayerRound.getInstance(players);
    }

    protected abstract void dealInitialCards();

    public void play() {
        while (!isGameOver()) {
            Player player = playerRound.getCurrentPlayer();
            player.play(cardDeck.lastThrownCard());
            playerRound.next();
        }
        announceWinner();
    }

    protected abstract boolean isGameOver();

    protected abstract Player getWinner();

    protected abstract void announceWinner();
}
