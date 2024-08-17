package org.example;

import org.example.game.UnoGame;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDriver {

    public static void main(String[] args) {
        // Create a list of players for the game
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Alice"));
        players.add(new Player(2, "Bob"));
        players.add(new Player(3, "Charlie"));

        // Initialize the Uno game with the list of players
        UnoGame unoGame = new UnoGame(players);

        // Start and run the game
        unoGame.play();
    }
}
