package main.CardModel;

import java.util.Random;

public enum Suit {
    DIAMONDS,
    SPADES,
    HEARTS,
    CLUBS;

    //get random rank
    private static final Random RAND = new Random();

    public static Suit randomSuit()  {
        Suit[] suits = values();
        return suits[RAND.nextInt(suits.length)];
    }
}
