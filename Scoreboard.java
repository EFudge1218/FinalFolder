import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Scoreboard {
    public Scoreboard() {
    }

    /**
*      * * This is the main method and is also used to create a scoreboard for the game.
     * @param args
     */
    public static void main(String[] args) {

                PriorityQueue<Integer> scoresQueue = new PriorityQueue<Integer>(); // Initialize the scores PriorityQueue
        GameLauncher gameLauncher = new GameLauncher(); // Create an instance of GameLauncher
        int[] scoring = gameLauncher.scoring; // Get the scores array (used with scoresQueue)
        int numPlayers = gameLauncher.numPlayers; // Get the number of players
        var playerNames = gameLauncher.playerAdd(); // Get the player names

        // Map to store player names as keys and their scores as values
        Map<String, Integer> playerScores = new HashMap<>();
        for (int i = 0; i < numPlayers; i++) {
            playerScores.put(playerNames.get(i), scoring[i]);
    }

    // PriorityQueue to sort players by their scores in descending order
        PriorityQueue<Map.Entry<String, Integer>> scoreboard = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed()
        );

        // Add all player-score entries to the PriorityQueue
        scoreboard.addAll(playerScores.entrySet());

        // Display the sorted scoreboard
        System.out.println("Scoreboard:");
        while (!scoreboard.isEmpty()) {
            Map.Entry<String, Integer> entry = scoreboard.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
