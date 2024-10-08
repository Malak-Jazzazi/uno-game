package org.example.card;

import java.util.Objects;

public class WildCard extends AbstractCard {

    public WildCard(CardType cardType) {
        super(CardColor.WILD, cardType);
    }

    @Override
    public String toString() {
        return "ActionCard{ "  +
                "CardColor=" + getCardColor() +
                ", CardType=" + getCardType() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionCard that = (ActionCard) o;
        return getCardType() == that.getCardType() && getCardColor() == that.getCardColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardType(), getCardColor());
    }
}
