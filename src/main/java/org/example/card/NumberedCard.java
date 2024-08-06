package org.example.card;

import java.util.Objects;

public class NumberedCard extends AbstractCard {
    private int value;

    public NumberedCard(CardColor cardColor, int number) {
        super(cardColor, CardType.NUMBER);
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberedCard that = (NumberedCard) o;
        return value == that.value && getCardColor() == that.getCardColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getCardColor());
    }

    @Override
    public String toString() {
        return "NumberedCard{" +
                "value=" + value +
                ", cardColor=" + getCardColor() +
                '}';
    }
}
