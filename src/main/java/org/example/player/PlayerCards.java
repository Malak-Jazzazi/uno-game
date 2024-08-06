package org.example.player;

import org.example.card.Card;
import org.example.card.CardColor;
import org.example.card.CardType;
import org.example.card.NumberedCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Card> ChooseCardToPlay(Card topCard){
        if(cards.isEmpty()) {
            throw new IllegalArgumentException("No cards available to play");
        }
        if(topCard == null){
            throw new IllegalArgumentException("topCard cannot be null");
        }
        for(Card card : cards){
            if(canPlayCard(card , topCard)){
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

    private boolean canPlayCard(Card card, Card topCard) {
        if (card.getCardColor() == CardColor.WILD) {
            return true;
        }
        if (card.getCardColor() == topCard.getCardColor() || card.getCardType() == topCard.getCardType()) {
            if (card.getCardType() == CardType.NUMBER) {
                return ((NumberedCard) card).getValue() == ((NumberedCard) topCard).getValue();
            }
            return true;
        }
        return false;
    }



}
