package org.example.game;

import org.example.player.Player;

import java.util.List;

public class BasicUnoGame extends Game2 {
    public BasicUnoGame(List<Player> players) {
        super(players);
    }

    @Override
    protected void dealInitialCards() {
        for(Player player : players){

        }
    }

    @Override
    protected boolean isGameOver() {
        return false;
    }

    @Override
    protected Player getWinner() {
        return null;
    }

    @Override
    protected void announceWinner() {

    }
}
