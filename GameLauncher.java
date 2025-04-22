import java.util.Scanner;

public class GameLauncher {
    private Scanner scanner; // Declare a single Scanner object

    GameLauncher() {
        // Initialize the Scanner object
        scanner = new Scanner(System.in);
    }

    boolean running;

    public static void main(String[] args) {
        GameLauncher game = new GameLauncher();
        game.running = true;
        game.playerAdd();
        game.launcher(game.running);
        game.scanner.close(); // Close the Scanner at the end of the program
    }

    /**
     * Method for implementing the main game launcher, 1 will keep running
     * boolean = true
     * 2 = false and exits the code
     * 
     * @param running
     * @return
     */
    public boolean launcher(boolean running) {
        try {
            if (this.running == true) {
                System.out.println("Please enter \n 1 for game selection \n 2 to exit menu");
                int menupick = scanner.nextInt(); // Use the shared Scanner object
                if (menupick == 1) {
                    @SuppressWarnings("unused")
                    GameChoices gameChoices = new GameChoices(); // Create an instance of GameChoices.
                    GameChoices.main(null); // Call the main method of GameChoices

                } else {
                    running = false;
                    this.running = false;
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        return running;
    }

    /**
     * method for adding players to the game
     */
    public void playerAdd() {
        System.out.print("Please enter number of players: ");
        int numPlayers = scanner.nextInt(); // Use the shared Scanner object
        scanner.nextLine();

        if (numPlayers == 1) {
            System.out.print("Player 1 enter name: ");
            String playerone = scanner.nextLine();
            System.out.println("Player 1 is: " + playerone);
        } else if (numPlayers == 2) {
            System.out.print("Player 1 enter name: ");
            String playerone = scanner.nextLine();
            System.out.print("Player 2 enter name: ");
            String playertwo = scanner.nextLine();
            System.out.println("Player 1 is: " + playerone + "\n" + "Player 2 is: " + playertwo);
        } else if (numPlayers == 3) {
            System.out.print("Player 1 enter name: ");
            String playerone = scanner.nextLine();
            System.out.print("Player 2 enter name: ");
            String playertwo = scanner.nextLine();
            System.out.print("Player 3 enter name: ");
            String playerthree = scanner.nextLine();
            System.out.println("Player 1 is: " + playerone + "\n" + "Player 2 is: " + playertwo + "\n" + "Player 3 is: " + playerthree);
        } else if (numPlayers == 4) {
            System.out.print("Player 1 enter name: ");
            String playerone = scanner.nextLine();
            System.out.print("Player 2 enter name: ");
            String playertwo = scanner.nextLine();
            System.out.print("Player 3 enter name: ");
            String playerthree = scanner.nextLine();
            System.out.print("Player 4 enter name: ");
            String playerfour = scanner.nextLine();
            System.out.println("Player 1 is: " + playerone + "\n" + "Player 2 is: " + playertwo + "\n" + "Player 3 is: " + playerthree + "\n" + "Player 4 is: " + playerfour);
        } else {
            System.err.println("Invalid number of players. Please enter 1, 2, 3, or 4.");
        }
    }
}