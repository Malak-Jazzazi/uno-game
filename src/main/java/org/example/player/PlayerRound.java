package org.example.player;

import java.util.List;

public class PlayerRound {
    private static volatile PlayerRound instance; // Volatile for thread-safety
    private final List<Player> players;
    private int current = 0;
    private Direction direction = Direction.CLOCKWISE;

    private PlayerRound(List<Player> players) {
        this.players = players;
    }

    public static PlayerRound getInstance(List<Player> players) {
        if (instance == null) {
            synchronized (PlayerRound.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new PlayerRound(players);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public Player getCurrentPlayer() {
        return players.get(current);
    }

    public Player getPlayerById(int playerId) {
        for (var player : players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }

    public void reverseDirection() {
        direction = direction.equals(Direction.CLOCKWISE) ? Direction.COUNTER_CLOCK_WISE : Direction.CLOCKWISE;
    }

    public void advanceToNextPlayer() {
        current = getNextIndex();
    }

    private int getNextIndex() {
        int increment = direction == Direction.CLOCKWISE ? 1 : -1;
        int nextIndex = (current + increment) % players.size();
        if (nextIndex < 0) {
            nextIndex += players.size(); // Wrap around for negative index
        }
        return nextIndex;
    }
}
