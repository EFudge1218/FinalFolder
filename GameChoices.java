import java.util.Scanner;

public class GameChoices {
    GameChoices() {
        // Constructor for GameChoices
    }

    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        try (Scanner gameselect = new Scanner(System.in)) {
            System.out.println(
                    "Please select Game \n 1 for  \n 2 for Tic Tac Toe  \n 3 for  \n 4 to return to Main Menu \n 5 to quit");
            int gamechosen = gameselect.nextInt();
            if (gamechosen == 1) {
                GameOne.main(args); // main method of GameOne
                return;
            }
            if (gamechosen == 2) {
                //calling and creating an instance of the tic tac toe class
                @SuppressWarnings("unused")
                TicTacToe tttg = new TicTacToe();
                TicTacToe.main(args);
                GameLauncher.main(args);
            }
            // if (gamechosen == 3) {
            // gamethree();
            // }
            if (gamechosen == 4) {
                System.out.println("Returning to main menu");
                GameLauncher.main(args); // Return to the main menu
                gameLauncher.launcher(true);

            }
            if (gamechosen == 5) {
                System.out.println("Thank you for playing, goodbye!");
    
                gameLauncher.running = false ; // Exit the game
                return;
            } else {
                System.out.println("Invalid choice, please try again.");
                main(args); // Restart the game selection

            }
        }
    }
}