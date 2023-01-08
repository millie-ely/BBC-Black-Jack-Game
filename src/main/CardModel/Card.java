package main.CardModel;

public class Card {
    Suit suit;
    public Rank rank;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String toString() {
        return (rank + " of " + suit);
    }
}