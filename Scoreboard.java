import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Scoreboard {
    private static Map<String, Integer> totalScores = new HashMap<>();
    private static PriorityQueue<Map.Entry<String, Integer>> scoreQueue = new PriorityQueue<>(
        Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed()
    );

    public Scoreboard() {
        // Constructor no longer needs to initialize scoreQueue since it's done statically
    }

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

        // Update priority queue with latest scores
        updateScoreQueue();

        // Display the sorted scoreboard
        displayScoreboard();
    }

    public static void updateScore(String playerName, int points) {
        totalScores.merge(playerName, points, Integer::sum);
        updateScoreQueue();
    }

    private static void updateScoreQueue() {
        scoreQueue.clear();
        scoreQueue.addAll(totalScores.entrySet());
    }

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

    public static Map<String, Integer> getScores() {
        return totalScores;
    }
}
