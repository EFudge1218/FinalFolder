import java.util.*;

public class Blackjack {

    //Uses Encapsulation because the private blackjackWins is accessed through the public method getWins 
    private static Map<String, Integer> blackjackWins = new HashMap<>();

    public static void main(String[] args) {
        playBlackjack(args);
    }

    private static void playBlackjack(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            //Get player information from GameLauncher
            GameLauncher gameLauncher = new GameLauncher();
            List<String> playerNames = gameLauncher.getPlayerNames();
            int numPlayers = playerNames.size();

            if (numPlayers < 1 || numPlayers > 4) {
                System.out.println("\nInvalid number of players. Exiting.");
                return;
            }

            System.out.println("\nWelcome to Blackjack!");
            System.out.println("Playing with " + numPlayers + " players:");
            for (String playerName : playerNames) {
                System.out.println("- " + playerName);
            }

            //Uses Collections import to shuffle the deck
            Stack<String> deck = createDeck();
            Collections.shuffle(deck);

            //Dealer's hand
            //Uses ArrayList to hold the cards in the dealers hand
            List<String> dealerHand = new ArrayList<>(Arrays.asList(deck.pop(), deck.pop()));
            System.out.println("\nDealer's first card: " + dealerHand.get(0));

            //Players' hands
            //Used ArrayList to hold the cards in the players hands and add more if they choose to hit
            List<List<String>> hands = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                hands.add(new ArrayList<>(Arrays.asList(deck.pop(), deck.pop())));
            }

            // Player turns
            for (int i = 0; i < numPlayers; i++) {
                System.out.println("\n" + playerNames.get(i) + "'s turn:");
                List<String> hand = hands.get(i);
                hand = playerTurn(scanner, hand, deck);
                hands.set(i, hand);
            }

            // Dealer's turn
            System.out.println("\nDealer's turn:");
            dealerHand = dealerTurn(dealerHand, deck);

            // Show hands and determine winner
            System.out.println("\nDealer's hand: " + dealerHand + " (Value: " + calculateHand(dealerHand) + ")");
            for (int i = 0; i < numPlayers; i++) {
                System.out.println(playerNames.get(i) + "'s hand: " + hands.get(i) + " (Value: " + calculateHand(hands.get(i)) + ")");
            }

            determineWinner(dealerHand, hands);

            // Add play again prompt
            System.out.println("\nWould you like to:");
            System.out.println("1. Play again");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your choice (1-2): ");
            
            scanner.nextLine(); // Clear the buffer
            String choice = scanner.nextLine().trim();

            if (choice.equals("2")) {
                System.out.println("\nReturning to main menu...");
                GameChoices.main(args);
                return;
            } else if (!choice.equals("1")) {
                System.out.println("\nInvalid choice. Returning to main menu...");
                GameChoices.main(args);
                return;
            }
        }
    }

    //Uses Abstraction to create the 52 card deck without listing out every possible card
    //Create deck using Stack, mimics Push and Pop functions when cards are drawn from the deck
    private static Stack<String> createDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        Stack<String> deck = new Stack<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                deck.push(rank + " of " + suit);
            }
        }
        return deck;
    }

    //Calculate hand value
    //Uses Abstraction to compute the hand value and Ace card value depending on the rest of the hand
    private static int calculateHand(List<String> hand) {
        int value = 0;
        int aceCount = 0;
        for (String card : hand) {
            String rank = card.split(" ")[0];
            if ("JQK".contains(rank)) {
                value += 10;
            } else if (rank.equals("A")) {
                value += 1;
                aceCount++;
            } else {
                value += Integer.parseInt(rank);
            }
        }
        while (aceCount > 0 && value + 10 <= 21) {
            value += 10;
            aceCount--;
        }
        return value;
    }

    //Player's turn
    //Uses Abstraction to handle the hit/stand behaviour during player hand
    private static List<String> playerTurn(Scanner scanner, List<String> hand, Stack<String> deck) {
        while (true) {
            System.out.println("\nYour hand: " + hand + " (Value: " + calculateHand(hand) + ")");
            System.out.print("\nHit or Stand? ");
            String action = scanner.next().toLowerCase();
            if (action.equals("hit")) {
                hand.add(deck.pop());
                if (calculateHand(hand) > 21) {
                    System.out.println("\nBust!");
                    return hand;
                }
            } else if (action.equals("stand")) {
                return hand;
            } else {
                System.out.println("\nInvalid input, please choose 'Hit' or 'Stand'.");
            }
        }
    }

    //Dealer's turn
    //Uses Abstraction for the dealers behaviour based on the rules of blackjack
    private static List<String> dealerTurn(List<String> hand, Stack<String> deck) {
        while (calculateHand(hand) < 17) {
            hand.add(deck.pop());
        }
        return hand;
    }

    //Determine winner
    //Uses Abstraction to determine the winner and assign the wins
    private static void determineWinner(List<String> dealerHand, List<List<String>> playerHands) {
        int dealerValue = calculateHand(dealerHand);
        GameLauncher gameLauncher = new GameLauncher();
        List<String> playerNames = gameLauncher.getPlayerNames();

        if (dealerValue > 21) {
            System.out.println("\nDealer busts! All players still in the game win!");
            // Award wins to all players who didn't bust
            for (int i = 0; i < playerHands.size(); i++) {
                int playerValue = calculateHand(playerHands.get(i));
                if (playerValue <= 21) {
                    String playerName = playerNames.get(i);
                    blackjackWins.merge(playerName, 1, Integer::sum);
                    Scoreboard.updateScore(playerName, 1);
                    System.out.println(playerName + " wins!");
                }
            }
            return;
        }

        for (int i = 0; i < playerHands.size(); i++) {
            String playerName = playerNames.get(i);
            int playerValue = calculateHand(playerHands.get(i));
            
            if (playerValue > 21) {
                System.out.println(playerName + " busts!");
            } else if (playerValue > dealerValue || dealerValue > 21) {
                System.out.println(playerName + " wins!");
                blackjackWins.merge(playerName, 1, Integer::sum);
                Scoreboard.updateScore(playerName, 1);
            } else if (playerValue == dealerValue) {
                System.out.println(playerName + " ties with the dealer.");
            } else {
                System.out.println(playerName + " loses.");
            }
        }
    }

    public static Map<String, Integer> getWins() {
        return blackjackWins;
    }
}