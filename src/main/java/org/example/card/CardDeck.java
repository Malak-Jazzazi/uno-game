package org.example.card;

import org.example.factory.ActionCardFactory;
import org.example.factory.CardFactory;
import org.example.factory.NumberedCardFactory;
import org.example.factory.WildCardFactory;

import java.util.Collections;
import java.util.Stack;

public final class CardDeck {
    private static volatile CardDeck instance; // Volatile for thread-safety
    private Stack<Card> cards;
    private Stack<Card> discardedCards;
    private CardColor deckColor;

    private CardDeck() {
        cards = new Stack<>();
        discardedCards = new Stack<>();
        initializeDeck();
        shuffle(cards);
    }

    public static CardDeck getInstance() {
        if (instance == null) {
            synchronized (CardDeck.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new CardDeck();
                }
            }
        }
        return instance;
    }

    public CardColor getDeckColor() {
        return deckColor;
    }

    public void setDeckColor(CardColor deckColor) {
        this.deckColor = deckColor;
    }

    private void initializeDeck() {
        for (CardColor color : CardColor.values()) {
            if (color != CardColor.WILD) {
                addNumberedCard(color);
                addActionCard(color);
            } else {
                addWildCard();
            }
        }
    }

    private void addNumberedCard(CardColor color) {
        CardFactory numberedCardFactory = new NumberedCardFactory();
        cards.push(new NumberedCard(color, 0));
        for (int i = 1; i <= 9; i++) {
            cards.push(numberedCardFactory.createCard(color, CardType.NUMBER, i));
            cards.push(numberedCardFactory.createCard(color, CardType.NUMBER, i));
        }
    }

    private void addActionCard(CardColor color) {
        CardFactory actionCardFactory = new ActionCardFactory();

        cards.push(actionCardFactory.createCard(color, CardType.REVERSE, 0));
        cards.push(actionCardFactory.createCard(color, CardType.REVERSE, 0));
        cards.push(actionCardFactory.createCard(color, CardType.DRAW_TWO, 0));
        cards.push(actionCardFactory.createCard(color, CardType.DRAW_TWO, 0));
        cards.push(actionCardFactory.createCard(color, CardType.SKIP, 0));
        cards.push(actionCardFactory.createCard(color, CardType.SKIP, 0));
    }

    private void addWildCard() {
        CardFactory wildCardFactory = new WildCardFactory();

        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_COLOR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_COLOR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_COLOR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_COLOR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_DRAW_FOUR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_DRAW_FOUR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_DRAW_FOUR, 0));
        cards.push(wildCardFactory.createCard(CardColor.WILD, CardType.WILD_DRAW_FOUR, 0));
    }

    private void shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card drawCardFromDeck() {
        if (cards.isEmpty()) {
            resetDeckFromDiscarded();
        }
        return cards.pop();
    }

    public void discardCard(Card card) {
        discardedCards.push(card);
        setDeckColor(card.getCardColor());
    }

    public Card lastThrownCard() {
        return discardedCards.peek();
    }

    private void resetDeckFromDiscarded() {
        if (discardedCards.isEmpty()) {
            throw new IllegalArgumentException("Discarded cards pile is empty, can't reset Deck");
        }
        Card lastCard = discardedCards.pop();
        shuffle(discardedCards);
        cards.addAll(discardedCards);
        discardedCards.clear();
        discardedCards.push(lastCard);
    }
}
