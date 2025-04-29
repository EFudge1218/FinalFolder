import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameLauncher {
    public int []scoring;
    public static int numPlayers;
    public static Object playerAdd;
    static String[] args;
     // Declare a single Scanner object
    GameLauncher() {
        // Initialize the Scanner object
       
    }

    static boolean running;

    public static void main(String[] args) {

        running = true;
        playerAdd();
        launcher(running);
    }

    /**
     * Method for implementing the main game launcher, 1 will keep running
     * boolean = true
     * 2 = false and exits the code
     * 
     * @param running
     * @return
     */
    public static boolean launcher(boolean running) {
        Scanner scanner = new Scanner(System.in);
        if (running == true) {
            System.out.println("Please enter \n 1 for game selection \n 2 to exit menu");
            int menupick = scanner.nextInt();
            if (menupick == 1) {
                GameChoices gameChoices = new GameChoices();
                gameChoices.main(args);
            }
            else if (menupick == 2) {
                System.out.println("Thank you for playing");
                // Display the scoreboard before exiting
                Scoreboard.displayScoreboard();
                running = false;
                System.exit(0);
            } else {
                System.out.println("Invalid choice, please try again.");
                launcher(running);
            }
        }
        return running;
    }

    private static List<String> playerNames = new ArrayList<>();
    public static List<String> playerAdd() {
    Scanner scanner = new Scanner(System.in);
    playerNames.clear(); // Clear any existing names
    while (true) {
        System.out.print("Please enter number of players (1-4): ");
        if (scanner.hasNextInt()) {
            numPlayers = scanner.nextInt();
            scanner.nextLine();
            if (numPlayers >= 1 && numPlayers <= 4) {
                break; // Exit the loop if the input is valid
            } else {
                System.err.println("Invalid number of players. Please enter a number between 1 and 4.");
            }
        } else {
            System.err.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    for (int i = 1; i <= numPlayers; i++) {
        System.out.print("Player " + i + " enter name: ");
        playerNames.add(scanner.nextLine());
    }

    System.out.println("Players:");
    for (int i = 0; i < playerNames.size(); i++) {
        System.out.println("Player " + (i + 1) + ": " + playerNames.get(i));
    }

        return playerNames; // Return the list of player names
    }

    public int getNumPlayers() {
        return numPlayers; // Return the number of players
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }
}