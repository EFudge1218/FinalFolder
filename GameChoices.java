import java.util.Scanner;

public class GameChoices {
   
    GameChoices() {

    }

    /**
     * Creating an instances of the Gamelauncher, Tictactoe and blackjack
     */
    public static void main(String[] args) {
        TicTacToe tttg = new TicTacToe(); // Create an instance of TicTacToe
        Blackjack bjack = new Blackjack();
        GameLauncher gameLauncher = new GameLauncher();
        Scanner scanner = new Scanner(System.in); // Initialize the Scanner object
        int playercount = gameLauncher.getNumPlayers(); // Get the number of players from GameLauncher
        System.out.println("Please select Game \n 1 for  \n 2 for Tic Tac Toe  \n 3 for  \n 4 to return to Main Menu \n 5 to quit");
        int gamechosen = scanner.nextInt();
        

        if (gamechosen == 1) {
            Blackjack.main(args); // main method of Blackjack
        } 
        else if (gamechosen == 2) {
            if (playercount == 2) {
                // Call the TicTacToe game
            tttg.main(args); // main method of TicTacToe
            } else {
                System.out.println("Tic Tac Toe is a two-player game, please select another game.");
                GameChoices.main(args); // Restart the game selection   
            } 
        }   
        else if (gamechosen == 3) {
            if (gameLauncher.numPlayers == 1) {
                Blackjack.main(args);
                GameChoices.main(args); // Return to the game selection
            } else if (gameLauncher.numPlayers > 4) {
                System.out.println("Too many players for this game, Maximum number is 4.");
                GameChoices.main(args); // Restart the game selection
            }
        } else if (gamechosen == 4) {
            System.out.println("Returning to main menu");
            GameLauncher.main(args); // Return to the main menu
            gameLauncher.launcher(true);
        } else {
            System.out.println("Invalid choice, please try again.");
            GameChoices.main(args); // Restart the game selection
        }
    }
}