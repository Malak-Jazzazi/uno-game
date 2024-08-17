package org.example.player;

import org.example.card.Card;
import org.example.card.CardColor;

import java.util.Iterator;
import java.util.Optional;

public class Player {
    private final int id ;
    private final String name;
    private PlayerCards playerCards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        playerCards = new PlayerCards();
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

    public Optional<Card> getCardFromHand(Card topCard , CardColor cardColor) {
        return playerCards.ChooseCardToPlay(topCard , cardColor);
    }
}
