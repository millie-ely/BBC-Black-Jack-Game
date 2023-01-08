package main;

import main.CardModel.Card;
import main.CardModel.Deck;
import main.CardModel.Rank;
import main.CardModel.Suit;
import main.DealerModel.Dealer;
import main.UserModel.Status;
import main.UserModel.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackFundamentals {

    private Deck deck;
    private User[] users;
    Scanner myObj = new Scanner(System.in);
    private Dealer dealer;
    public int dealerSum = 0;
    public Card cardDealtOne;

    public void introductionAndSetup() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("How many people will be playing?");
        int players = myObj.nextInt();

        System.out.println("The rules are as follow:");
        System.out.println("The dealer will be dealt cards and one will be revealed to help influence your choices.");
        System.out.println("Each player will then initially dealt two cards.");
        System.out.println("From there a choice of 'hit' or 'stand' is chosen");
        System.out.println("");
        System.out.println("HIT: draw another card.");
        System.out.println(
                "Each draw will add to the total of your hand, with cards being equal to face value and ACE being either 11 or 1 (your choice).");
        System.out.println("By drawing and exceeding 21 will cause you to immediately go BUST!");
        System.out.println("");
        System.out.println("STAND: stop drawing cards.");
        System.out.println("");
        System.out.println(
                "The Dealers hand will then be revealed, a higher score from the players or a score of 21 will result in a WIN!");
        System.out.println("Good Luck!");
        System.out.println("");

        users = new User[players];
        for (int i = 0; i < players; i++) {
            System.out.print("What is player " + (i + 1) + "'s name? ");
            String names = myObj.next();
            users[i] = new User();
            users[i].setName(names);
            users[i].setStatus(Status.PROGRESS);
        }
        createDeck();
    }

    public Deck createDeck() {
        deck = new Deck();
        return deck;
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void createHands() {
        for (User user : users) {
            if (user.getStatus().getValue() == 0) {
                Card cardDealtOne = deck.dealCard();
                Card cardDealtTwo = deck.dealCard();
                System.out.println(user.getName() + " has been dealt the " + getSuitUnicode(cardDealtOne) + cardDealtOne
                        + " and the " + getSuitUnicode(cardDealtTwo) + cardDealtTwo + ".");
                if (cardDealtOne.rank.equals(Rank.ACE)) {
                    System.out.println(user.getName()
                            + " has an ACE in their hand. This will be calculated as a 1 until you stand, then the choice of 1 or 11 will be given ");
                }
                if (cardDealtTwo.rank.equals(Rank.ACE)) {
                    System.out.println(user.getName()
                            + " has an ACE in their hand. This will be calculated as a 1 until you stand, then the choice of 1 or 11 will be given  ");
                }
                user.addToHand(cardDealtOne);
                user.addToHand(cardDealtTwo);
            }
        }
    }

    public void hitOrStand() {
        for (User user : users) {
            if (user.getStatus().getValue() == 0) {
                String option;
                do {
                    do {
                        System.out.print(user.getName() + " your current total is " + userHandTotal(user)
                                + " would you like to HIT or STAND? ");
                        option = myObj.next();
                    } while (!(option.equalsIgnoreCase("hit") || option.equalsIgnoreCase("stand")));
                    if (option.equalsIgnoreCase("hit")) {
                        Card cardDealt = deck.dealCard();
                        System.out.println(
                                user.getName() + " has been dealt the " + getSuitUnicode(cardDealt) + cardDealt + ".");
                        if (cardDealt.rank.equals(Rank.ACE)) {
                            System.out.println(user.getName()
                                    + " has been dealt an ACE the value of this can be decided when you stand ");
                        }
                        user.addToHand(cardDealt);
                        System.out.println(user.getName() + "'s hand now consists of " + user.getHand());
                        if (userHandTotal(user) > 21) {
                            playerBust(user);
                            break;
                        }
                    }
                } while ((!option.equalsIgnoreCase("stand")));
                for (Card card : user.getHand()) {
                    if (card.rank.equals(Rank.ACE)) {
                        System.out.println("What value would you like the " + card + " to be 1 or 11?");
                        int aceOption = myObj.nextInt();
                        if (aceOption == 11) {
                            card = aceRankEleven(card);
                        }
                    }
                }
            }
        }
    }

    public Card aceRankEleven(Card cardDealt) {
        cardDealt.rank.setValue(11);
        return cardDealt;
    }

    public int userHandTotal(User user) {
        int sum = 0;
        for (int i = 0; i < user.getHand().size(); i++)
            sum += user.getHand().get(i).rank.getValue();
        return sum;
    }

    public void dealerFirstCard() {
        dealer = new Dealer();
        dealer.setStatus(Status.PROGRESS);
        cardDealtOne = deck.dealCard();
        System.out.println(
                "The Dealer's first card has been revealed and is the " + getSuitUnicode(cardDealtOne) + cardDealtOne);
    }

    public void dealerDealt() {
        Card cardDealtTwo = deck.dealCard();
        System.out.println("The Dealer's second card has been revealed and is the " + getSuitUnicode(cardDealtTwo)
                + cardDealtTwo + ".");
        if (cardDealtOne.rank.equals(Rank.ACE) && cardDealtTwo.rank.getValue() < 10) {
            cardDealtOne = aceRankEleven(cardDealtOne);
        } else if (cardDealtTwo.rank.equals(Rank.ACE) && cardDealtOne.rank.getValue() < 10) {
            cardDealtTwo = aceRankEleven(cardDealtTwo);
        }
        dealer.addToHand(cardDealtOne);
        dealer.addToHand(cardDealtTwo);

        for (int i = 0; i < dealer.getHand().size(); i++)
            dealerSum += dealer.getHand().get(i).rank.getValue();
        System.out.println("The dealer's hand consists of " + dealer.getHand() + " with a total of " + dealerSum + ".");
        while (dealerSum < 17) {
            Card extraCard = deck.dealCard();
            dealer.addToHand(extraCard);
            dealerSum += extraCard.rank.getValue();
            System.out.println("The dealer has picked up the " + extraCard + " making their total " + dealerSum + ".");
        }
        if (dealerSum > 21) {
            dealer.setStatus(Status.BUST);
            System.out.println("The dealer has gone BUST! ");
        } else if (dealerSum == 21) {
            dealer.setStatus(Status.WIN);
        }

    }

    public void evaluateStatus() {
        List<User> winning = new ArrayList<>();
        for (User user : users) {
            user.setTotal(userHandTotal(user));
            if (userHandTotal(user) == 21) {
                playerWin(user);
                winning.add(user);
            }
        }
        if (winning.size() == 1 && dealer.getStatus().equals(Status.WIN)) {
            System.out.println("The Dealer has drawn with " + winning.get(0).getName() + ", Well done! ");
        } else if (winning.size() == 1) {
            System.out.println("Congratulations " + winning.get(0).getName() + " has beat The Dealer! ");
        } else if (winning.size() > 1 && dealer.getStatus().equals(Status.WIN)) {
            System.out.println("The Dealer has drawn with multiple people, congratulations "
                    + winning.stream().map(User::getName).toList());
        } else if (winning.size() > 1) {
            System.out.println("Congratulations " + winning.stream().map(User::getName).toList()
                    + " multiple people have beat The Dealer! ");
        } else {
            int maxValue = 0;
            List<User> maxTotalUser = new ArrayList<>();
            for (User user : users) {
                if (!user.getStatus().equals(Status.BUST)) {
                    if (user.getTotal() > maxValue) {
                        maxValue = user.getTotal();
                    }
                }
            }
            for (User user : users) {
                if (user.getTotal() == maxValue) {
                    maxTotalUser.add(user);
                }
            }

            if (maxTotalUser.size() == 0 && dealer.getStatus() != Status.BUST) {
                System.out.println("All players went bust and The Dealer has won! ");
            } else if (maxTotalUser.size() == 0 && dealer.getStatus().equals(Status.BUST)) {
                System.out.println("Oh No! All players and The Dealer went Bust, no one has won the game ");
            } else if (dealer.getStatus() != Status.BUST && maxTotalUser.get(0).getTotal() < dealerSum) {
                System.out.println("The Dealer has won with a total of " + dealerSum);
            } else if (dealer.getStatus() != Status.BUST && maxTotalUser.get(0).getTotal() == dealerSum) {
                System.out.println("The Dealer and " + maxTotalUser.stream().map(User::getName).toList().toString()
                        + " has DRAWN with a total of " + dealerSum);
            } else {
                System.out.println(maxTotalUser.stream().map(User::getName).toList().toString()
                        + " has won with a total of " + maxTotalUser.get(0).getTotal() + ". Well Done!");
            }
        }
    }

    private void playerBust(User user) {
        System.out.println("Oh No! " + user.getName()
                + " has gone BUST! your total is over 21. You have been removed from the game.");
        user.setStatus(Status.BUST);
    }

    private void playerWin(User user) {
        System.out.println(user.getName() + "'s total is 21!");
        user.setStatus(Status.WIN);
    }

    public String getSuitUnicode(Card card) {
        if (card.getSuit().equals(Suit.DIAMONDS)) {
            return "\u2666";
        } else if (card.getSuit().equals(Suit.CLUBS)) {
            return "\u2663";
        } else if (card.getSuit().equals(Suit.SPADES)) {
            return "\u2660";
        } else {
            return "\u2665";
        }
    }

    public void endGame() {
        System.out.println("Thank you for playing!");
    }
}
