import java.util.*;

class SolitaireRules {

    public boolean isValidMoveToFoundation(Card card, Stack<Card> foundation) {
        if (foundation.isEmpty()) {
            return "A".equals(card.getRank()); //Foundation starts with an Ace
        } else {
            Card topCard = foundation.peek();
            return card.getSuit().equals(topCard.getSuit()) && 
                   card.getRankValue() == topCard.getRankValue() + 1;
        }
    }

    public boolean isValidMoveToTableau(Card card, Stack<Card> tableauColumn) {
        if (tableauColumn.isEmpty()) {
            return "K".equals(card.getRank()); // Empty tableau column starts with a King
        } else {
            Card topCard = tableauColumn.peek();
            return !card.getSuit().equals(topCard.getSuit()) && 
                   card.getRankValue() + 1 == topCard.getRankValue();
        }
    }

    public boolean canFlipCard(Stack<Card> tableauColumn) {
        return !tableauColumn.isEmpty(); // Can flip only if the column is not empty
    }

    public int getRankValue(String rank) {
        switch (rank) {
            case "A":
                return 1;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                return Integer.parseInt(rank);
        }
    }
}

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getRankValue() {
        SolitaireRules rules = new SolitaireRules();
        return rules.getRankValue(rank);
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

public class Solitaire {
    private Stack<Card>[] tableau; // 7 columns
    private Stack<Card>[] foundations; // 4 suit piles
    private Stack<Card> waste;
    private Stack<Card> deck;
    private SolitaireRules rules;

    public Solitaire() {
        rules = new SolitaireRules();
        initializeGame();
    }

    private void initializeGame() {
        tableau = new Stack[7];
        foundations = new Stack[4];
        waste = new Stack<>();
        deck = createDeck();
        Collections.shuffle(deck);

        // Deal cards to tableau
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = new Stack<>();
            for (int j = 0; j <= i; j++) {
                tableau[i].push(deck.pop());
            }
        }

        // Initialize foundations
        for (int i = 0; i < foundations.length; i++) {
            foundations[i] = new Stack<>();
        }
    }

    private Stack<Card> createDeck() {
        Stack<Card> deck = new Stack<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.push(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void displayGameState() {
        System.out.println("Tableau:");
        for (int i = 0; i < tableau.length; i++) {
            System.out.println("Column " + (i + 1) + ": " + tableau[i]);
        }
        System.out.println("Foundations:");
        for (int i = 0; i < foundations.length; i++) {
            System.out.println("Foundation " + (i + 1) + ": " + foundations[i]);
        }
        System.out.println("Waste: " + waste);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayGameState();
            System.out.println("Enter move (e.g., 'move 1 to foundation' or 'flip 1'): ");
            String move = scanner.nextLine();
            handleMove(move);
            if (checkWinCondition()) {
                System.out.println("Congratulations! You win!");
                break;
            }
        }
        scanner.close();
    }

    private void handleMove(String move) {
        String[] parts = move.split(" ");
        if (parts.length < 2) {
            System.out.println("Invalid move format.");
            return;
        }
        switch (parts[0].toLowerCase()) {
            case "move":
                // Implement the move logic here
                System.out.println("Move logic to be added here...");
                break;
            case "flip":
                // Example: Flip top card on a tableau column
                int column = Integer.parseInt(parts[1]) - 1;
                if (rules.canFlipCard(tableau[column])) {
                    System.out.println("Flipping card...");
                } else {
                    System.out.println("Cannot flip card.");
                }
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private boolean checkWinCondition() {
        for (Stack<Card> foundation : foundations) {
            if (foundation.size() < 13) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solitaire game = new Solitaire();
        game.play();
    }
}