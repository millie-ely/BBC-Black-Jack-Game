import main.BlackJackFundamentals;

public class App {
    public static void main(String[] args) {
        BlackJackFundamentals fundamentals = new BlackJackFundamentals();
        fundamentals.introductionAndSetup();
        fundamentals.shuffleDeck();
        fundamentals.dealerFirstCard();
        fundamentals.createHands();
        fundamentals.hitOrStand();
        fundamentals.dealerDealt();
        System.out.println("----THE RESULTS----");
        fundamentals.evaluateStatus();
        fundamentals.endGame();
    }
}
