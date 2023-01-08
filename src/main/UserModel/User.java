package main.UserModel;

import main.CardModel.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    List<Card> hand = new ArrayList<>();
    Status status;
    int total;

    public void setName(String nameGiven) {
        name = nameGiven;
    }

    public String getName(){ return name;
    }

    public Status getStatus(){ return status;
    }

    public void setStatus(Status statusUpdate) {
        status = statusUpdate;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setTotal(int totalScore) {
        total = totalScore;
    }

    public int getTotal() {
        return total;
    }

}