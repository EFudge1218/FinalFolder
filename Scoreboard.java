import java.util.PriorityQueue;

public class Scoreboard {
    public Scoreboard() {
    }

    /**
     * * This is the main method and is also used to create a scoreboard for the game.
     * @param args
     */
    public static void main(String[] args) {
        
        PriorityQueue<Integer> scoreboard = new PriorityQueue<Integer>(); // Initialize the scores PriorityQueue
        GameLauncher gameLauncher = new GameLauncher(); // Create an instance of GameLauncher
        int []scoring = gameLauncher.scoring; // making the scores array
        int numPlayers = gameLauncher.numPlayers;
        scoreboard.add(scoring[0]);
        scoreboard.add(scoring[1]); // Add a score to the queue
        //scoring(); //calling the scoring method
    }

    //public void scoring() {
        
       // scoreboard.add(2);// Add a score to the queue
     //   scoreboard.add(2); // Add a score to the queue
   // }
}
