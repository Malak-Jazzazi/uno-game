package org.example.player;

import org.example.card.Card;

import java.util.Iterator;
import java.util.Optional;

public class Player {
    private final int id ;
    private final String name;
    private PlayerCards playerCards;

    public Player(int id, String name, PlayerCards playerCards) {
        this.id = id;
        this.name = name;
        this.playerCards = playerCards;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerCards getPlayerCards() {
        return playerCards;
    }

    public void addCardToHand(Card card) {
        playerCards.addCard(card);
    }

    public void removeCardFromHand(Card card) {
        playerCards.playCard(card);
    }

    public Optional<Card> play(Card topCard) {
        return playerCards.chooseCardToPlay(topCard);
    }
}
