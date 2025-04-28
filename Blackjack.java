import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Blackjack!");
        System.out.print("\nEnter the number of players (1-4): ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 1 || numPlayers > 4) {
            System.out.println("\nInvalid number of players. Exiting.");
            return;
        }

        List<String> deck = createDeck();
        Collections.shuffle(deck);

        Map<String, List<String>> hands = new HashMap<>();
        List<String> dealerHand = new ArrayList<>(Arrays.asList(deck.remove(0), deck.remove(0)));
        hands.put("\nDealer", dealerHand);

        // Show dealer's first card
        System.out.println("\nDealer's first card: " + dealerHand.get(0));

        for (int i = 1; i <= numPlayers; i++) {
            hands.put("\nPlayer " + i, new ArrayList<>(Arrays.asList(deck.remove(0), deck.remove(0))));
        }

        // Player turns
        for (int i = 1; i <= numPlayers; i++) {
            String player = "\nPlayer " + i;
            List<String> hand = new ArrayList<>(hands.get(player));
            System.out.println(player + "'s turn:");
            hand = playerTurn(scanner, hand, deck);
            hands.put(player, hand);
            System.out.println();
        }

        //Dealer's turn
        System.out.println("\nDealer's turn:");
        dealerHand = dealerTurn(dealerHand, deck);
        hands.put("\nDealer", dealerHand);
        System.out.println();

        //Show hands and determine winner
        for (String player : hands.keySet()) {
            List<String> hand = hands.get(player);
            System.out.println(player + " hand: " + hand + " (Value: " + calculateHand(hand) + ")");
        }

        determineWinner(hands);
    }

    // Create deck
    private static List<String> createDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        List<String> deck = new ArrayList<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    //Calculate hand value
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
        // Adjust for Aces
        while (aceCount > 0 && value + 10 <= 21) {
            value += 10;
            aceCount--;
        }
        return value;
    }

    //Player's turn
    private static List<String> playerTurn(Scanner scanner, List<String> hand, List<String> deck) {
        while (true) {
            System.out.println("\nYour hand: " + hand + " (Value: " + calculateHand(hand) + ")");
            System.out.print("\nHit or Stand? ");
            String action = scanner.next().toLowerCase();
            if (action.equals("hit")) {
                hand.add(deck.remove(0));
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

    // Dealer's turn
    private static List<String> dealerTurn(List<String> hand, List<String> deck) {
        while (calculateHand(hand) < 17) {
            hand.add(deck.remove(0));
        }
        return hand;
    }

    // Determine winner
    private static void determineWinner(Map<String, List<String>> hands) {
        int dealerValue = calculateHand(hands.get("\nDealer"));
        if (dealerValue > 21) {
            System.out.println("\nDealer busts! All players still in the game win!");
            return;
        }

        for (String player : hands.keySet()) {
            if (player.equals("\nDealer")) continue;
            int playerValue = calculateHand(hands.get(player));
            if (playerValue > 21) {
                System.out.println(player + " busts!");
            } else if (playerValue > dealerValue || dealerValue > 21) {
                System.out.println(player + " wins!");
            } else if (playerValue == dealerValue) {
                System.out.println(player + " ties with the dealer.");
            } else {
                System.out.println(player + " loses.");
            }
        }
    }
}