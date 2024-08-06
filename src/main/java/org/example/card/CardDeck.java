package org.example.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private static CardDeck instance;
    private Stack<Card> cards;
    private Stack<Card> discardedCards;

    private CardDeck() {
        cards = new Stack<>();
        discardedCards = new Stack<>();
        initializeDeck();
        shuffle(cards);
    }


    public static CardDeck getInstance() {
        if (instance == null) {
            instance = new CardDeck();
        }
        return instance;
    }

    private void initializeDeck() {
        for(CardColor color : CardColor.values()) {
            if(color != CardColor.WILD){
                addNumberedCard(color);
                addActionCard(color);
            }else{
                addWildCard();
            }
        }
    }

    private void addNumberedCard(CardColor color) {
        cards.push(new NumberedCard(color , 0));
        for(int i = 1 ; i <= 9 ; i++) {
            cards.push(new NumberedCard(color , i));
            cards.push(new NumberedCard(color , i));
        }

    }

    private void addActionCard(CardColor color) {
        cards.push(new ActionCard(color , CardType.REVERSE));
        cards.push(new ActionCard(color , CardType.REVERSE));
        cards.push(new ActionCard(color , CardType.DRAW_TWO));
        cards.push(new ActionCard(color , CardType.DRAW_TWO));
        cards.push(new ActionCard(color , CardType.SKIP));
        cards.push(new ActionCard(color , CardType.SKIP));
    }

    private void addWildCard() {
        cards.push(new WildCard(CardType.WILD_COLOR));
        cards.push(new WildCard(CardType.WILD_COLOR));
        cards.push(new WildCard(CardType.WILD_COLOR));
        cards.push(new WildCard(CardType.WILD_COLOR));
        cards.push(new WildCard(CardType.WILD_DRAW_FOUR));
        cards.push(new WildCard(CardType.WILD_DRAW_FOUR));
        cards.push(new WildCard(CardType.WILD_DRAW_FOUR));
        cards.push(new WildCard(CardType.WILD_DRAW_FOUR));
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
    }

    public Card lastThrownCard() {
        return discardedCards.peek();
    }

    private void resetDeckFromDiscarded() {
        if(discardedCards.isEmpty()){
            throw new IllegalArgumentException("Discarded cards pile is empty can't reset Deck");
        }
        Card lastCard = discardedCards.pop();
        shuffle(discardedCards);
        cards.addAll(discardedCards);
        discardedCards.clear();
        discardedCards.push(lastCard);
    }

}
