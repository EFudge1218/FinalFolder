import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * This class that manages and displays game scores across multiple games.
 * Comines scores from different games (TicTacToe, Blackjack, Country Guesser)
 * and controls a scoreboard for all players using a priority queue.
 */
public class Scoreboard {
    /** Stores the total wins for each player across all games */
    private static Map<String, Integer> totalScores = new HashMap<>();
    
    /** Priority queue to maintain scores in descending order */
    private static PriorityQueue<Map.Entry<String, Integer>> scoreQueue = new PriorityQueue<>(
        Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed()
    );

    /**
     * Constructor for scoreboard class
     * Queue initialization
     */
    public Scoreboard() {
        // Constructor no longer needs to initialize scoreQueue since it's done statically
    }

    /**
     * Main method to initialize and display the scoreboard.
     * Retrieves player names, sets their scores, and combines
     * scores from all games before displaying the final scoreboard.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        List<String> playerNames = gameLauncher.playerAdd();
        
        // Initialize scores for all players
        for (String player : playerNames) {
            totalScores.put(player, 0);
        }

        // Create priority queue for sorting
        scoreQueue = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed()
        );

        // Add game-specific scores (TicTacToe, etc)
        Map<String, Integer> tictactoeWins = TicTacToe.getWins();
        for (Map.Entry<String, Integer> entry : tictactoeWins.entrySet()) {
            totalScores.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        // Add Blackjack wins to total scores
        Map<String, Integer> blackjackWins = Blackjack.getWins();
        for (Map.Entry<String, Integer> entry : blackjackWins.entrySet()) {
            totalScores.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        // Add Country Guesser wins to total scores
        Map<String, Integer> countryGuesserWins = CountryGuesserGame.getWins();
        for (Map.Entry<String, Integer> entry : countryGuesserWins.entrySet()) {
            totalScores.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        // Add Connect Four wins to total scores
        Map<String, Integer> connectFourWins = ConnectFourWithGraph.getWins();
        for (Map.Entry<String, Integer> entry : connectFourWins.entrySet()) {
            totalScores.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        // Update priority queue with latest scores
        updateScoreQueue();

        // Display the sorted scoreboard
        displayScoreboard();
    }

    /**
     * Updates a player's score by adding points to their total.
     * Automatically updates the priority queue after modifying scores.
     *
     * @param playerName The name of the player whose score is being updated
     * @param points The number of points to add to the player's score
     */
    public static void updateScore(String playerName, int points) {
        totalScores.merge(playerName, points, Integer::sum);
        updateScoreQueue();
    }

    /**
     * Updates the priority queue with the latest scores from totalScores.
     *
     */
    private static void updateScoreQueue() {
        scoreQueue.clear();
        scoreQueue.addAll(totalScores.entrySet());
    }

    /**
     * Displays the current scoreboard in descending order
     */
    public static void displayScoreboard() {
        if (scoreQueue.isEmpty()) {
            System.out.println("\n=== FINAL SCOREBOARD ===");
            System.out.println("No games have been played yet!");
            return;
        }

        System.out.println("\n=== FINAL SCOREBOARD ===");
        System.out.println("Player\t\tTotal Wins");
        System.out.println("------------------------");
        
        // Create temporary queue to preserve the original
        PriorityQueue<Map.Entry<String, Integer>> tempQueue = new PriorityQueue<>(scoreQueue);
        
        while (!tempQueue.isEmpty()) {
            Map.Entry<String, Integer> entry = tempQueue.poll();
            System.out.printf("%-15s %d%n", entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the current total scores for all players.
     *
     * @return A Map containing player names and their total scores
     */
    public static Map<String, Integer> getScores() {
        return totalScores;
    }
}
