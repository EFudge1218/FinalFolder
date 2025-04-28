import java.util.PriorityQueue;

public class Scoreboard {
    public Scoreboard() {
    }

    /**
     * Creating the game launcher instance to be used for this class
     */
    GameLauncher gameLauncher = new GameLauncher();

    public static void main(String[] args) {

    }

    public void scoring() {
        PriorityQueue<Integer> scoreboard = new PriorityQueue<Integer>(); // Initialize the scores PriorityQueue
        scoreboard.add(GameLauncher);// Add a score to the queue
        scoreboard.add(GameLauncher.scoring); // Add a score to the queue
    }
}
