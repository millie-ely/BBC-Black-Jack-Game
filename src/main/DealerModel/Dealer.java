package main.DealerModel;

import java.util.ArrayList;
import java.util.List;

import main.CardModel.Card;
import main.UserModel.Status;

public class Dealer {
    String name;
    List<Card> hand = new ArrayList<>();
    Status status;

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
}
