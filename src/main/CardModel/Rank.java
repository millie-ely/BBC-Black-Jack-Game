package main.CardModel;

import java.util.Random;

public enum Rank {
    ACE(1),
    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    int value;
    Rank(int v) {
        value = v;
    }
    public int getValue() {
        return value;
    }

    //set rank - applicable for ace
    public void setValue(int aceValue) {
        value = aceValue;
    }

    //get random rank
    private static final Random RAND = new Random();

    public static Rank randomRank()  {
        Rank[] ranks = values();
        return ranks[RAND.nextInt(ranks.length)];
    }


}