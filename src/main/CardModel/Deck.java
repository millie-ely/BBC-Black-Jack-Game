package main.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck;

    //create deck of all cards
    public Deck() {
       this.deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card();
                card.setSuit(suit);
                card.setRank(rank);
                deck.add(card);
            }
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    //output deck as String
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Card card : deck) {
            output.append(card);
            output.append("\n");
        }
        return output.toString();
    }

    //shuffle cards
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //deal card - remove top one after shuffle (add to players hand)
    public Card dealCard(){
        return deck.remove(0);
    }

    //deck size
    public int deckSize() {
        return deck.size();
    }
}
