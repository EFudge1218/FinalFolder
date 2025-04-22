import java.util.PriorityQueue;
import java.util.Scanner;

public class Scoreboard {
    int scoreboard;
    Scoreboard(){

    }
    public static void main(String[] args) {
        PriorityQueue<Integer> scoreboard = new PriorityQueue<>();
            Scanner players = new Scanner(System.in);
            System.out.print("Player 1 enter name");
            String playerone = players.toString();
            System.out.print("Player 2 enter name");
            String playertwo = players.toString();
            System.out.println(players);

    }
}
