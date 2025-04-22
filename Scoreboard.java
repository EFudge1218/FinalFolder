import java.util.PriorityQueue;
import java.util.Scanner;

public class Scoreboard {
    // Class variable if needed
    public PriorityQueue<Integer> scores;
    int leaderboard;
    int wins;


    // Constructor
    public Scoreboard() {
    }

    public static void main(String[] args) {
        Scanner players = new Scanner(System.in);
        System.out.print("Player 1 enter name: ");
        String playerone = players.next();
        System.out.print("Player 2 enter name: ");
        String playertwo = players.next();
        System.out.println(playertwo + playerone);
        players.close();
    }
    public int addWins(int wins) {
        this.wins = wins;
        return wins;
    }
    public int getWins() {
        return wins;
    }
}
