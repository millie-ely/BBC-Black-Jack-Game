package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import main.BlackJackFundamentals;
import main.CardModel.Deck;
import main.CardModel.Rank;
import main.CardModel.Suit;
import main.DealerModel.Dealer;
import main.UserModel.User;
import main.CardModel.Card;

public class TestCases {
    BlackJackFundamentals fundamentals = new BlackJackFundamentals();
    
    @Test
    public void isFiftyTwoCards() {
        Deck deck = new Deck();
        assertEquals(52, deck.deckSize());
    }

    @Test 
    public void isShuffled() {
        Deck orderedDeck = new Deck();
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();

        assertNotEquals(orderedDeck, shuffledDeck);
    }

    @Test 
    public void isTopCardTaken() {
        Deck deck = new Deck();
        Card expectedCard = deck.getDeck().get(0);
        
        assertEquals(expectedCard, deck.dealCard());
    }

    @Test 
    public void isAddedToUserHand() {
        User testUser = new User();
        Deck deck = new Deck();
        deck.shuffle();
        Card expectedCard = deck.getDeck().get(0);
        testUser.addToHand(deck.dealCard());
        Card actualCard = testUser.getHand().get(0);

        assertEquals(expectedCard, actualCard);
    }

    @Test 
    public void isAddedToDealerHand() {
        Dealer testDealer = new Dealer();
        Deck deck = new Deck();
        deck.shuffle();
        Card expectedCard = deck.getDeck().get(0);
        testDealer.addToHand(deck.dealCard());
        Card actualCard = testDealer.getHand().get(0);

        assertEquals(expectedCard, actualCard);
    }

    @Test
    public void isAceValueChanged() {
        Card actualCard = new Card();
        actualCard.setSuit(Suit.randomSuit());
        actualCard.setRank(Rank.ACE);
        fundamentals.aceRankEleven(actualCard);

        assertEquals(11, actualCard.rank.getValue());
    }

    @Test 
    public void totalSum() {
        User testUser = new User();
        Card tenDiamonds = new Card();
        tenDiamonds.setSuit(Suit.DIAMONDS);
        tenDiamonds.setRank(Rank.TEN);
        Card aceClubs = new Card();
        aceClubs.setSuit(Suit.CLUBS);
        aceClubs.setRank(Rank.ACE);
        Card threeClubs = new Card();
        threeClubs.setSuit(Suit.CLUBS);
        threeClubs.setRank(Rank.THREE);

        testUser.addToHand(tenDiamonds);
        testUser.addToHand(aceClubs);
        testUser.addToHand(threeClubs);

        assertEquals(14, fundamentals.userHandTotal(testUser));
    }

    @Test 
    public void diamondSuitUnicode() {
        Card card = new Card();
        card.setSuit(Suit.DIAMONDS);

        assertEquals("\u2666", fundamentals.getSuitUnicode(card));
    }

    @Test 
    public void clubSuitUnicode() {
        Card card = new Card();
        card.setSuit(Suit.CLUBS);

        assertEquals("\u2663", fundamentals.getSuitUnicode(card));
    }

    @Test 
    public void spadeSuitUnicode() {
        Card card = new Card();
        card.setSuit(Suit.SPADES);

        assertEquals("\u2660", fundamentals.getSuitUnicode(card));
    }

    @Test 
    public void heartSuitUnicode() {
        Card card = new Card();
        card.setSuit(Suit.HEARTS);

        assertEquals("\u2665", fundamentals.getSuitUnicode(card));
    }

}
