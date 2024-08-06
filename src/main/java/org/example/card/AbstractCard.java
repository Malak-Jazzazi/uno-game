package org.example.card;

public abstract class AbstractCard implements Card {
    private CardType cardType;
    private CardColor cardColor;

    protected AbstractCard(CardColor cardColor, CardType cardType) {
        this.cardColor = cardColor;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public abstract boolean equals(Object obj);
    public abstract int hashCode();
    public abstract String toString();
}
