package org.example.player;

import org.example.card.Card;
import org.example.card.CardColor;
import org.example.card.CardType;
import org.example.card.NumberedCard;

import java.util.*;

public class PlayerCards {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card){
        if(card == null){
            throw new IllegalArgumentException("card is null");
        }
        cards.add(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public void playCard(Card card){
        if(card == null){
            throw new IllegalArgumentException("card is null");
        }
        if(cards.contains(card)){
            cards.remove(card);
        }else{
            throw new IllegalArgumentException("card is not in card list");
        }
    }

    public Optional<Card> ChooseCardToPlay(Card topCard , CardColor cardColor){
        if(cards.isEmpty()) {
            throw new IllegalArgumentException("No cards available to play");
        }
        if(topCard == null){
            throw new IllegalArgumentException("topCard cannot be null");
        }
        for(Card card : cards){
            if(canPlayCard(card , topCard , cardColor)){
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

    public CardColor chooseColorForWildCard() {
        Map<CardColor, Integer> colorCount = new HashMap<>();

        for (Card card : cards) {
            if (card.getCardColor() != CardColor.WILD) {
                colorCount.put(card.getCardColor(), colorCount.getOrDefault(card.getCardColor(), 0) + 1);
            }
        }

        CardColor mostFrequentColor = null;
        int maxCount = 0;

        for (Map.Entry<CardColor, Integer> entry : colorCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentColor = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentColor != null ? mostFrequentColor : CardColor.RED;
    }

    private boolean canPlayCard(Card card, Card topCard, CardColor currentColor) {
        if (card.getCardColor() == CardColor.WILD) {
            return true;
        }

        if (card.getCardColor() == currentColor) {
            return true;
        }

        if (card.getCardType() == topCard.getCardType()) {
            if (card.getCardType() == CardType.NUMBER) {
                return ((NumberedCard) card).getValue() == ((NumberedCard) topCard).getValue();
            }
            return true;
        }

        return false;
    }



}
