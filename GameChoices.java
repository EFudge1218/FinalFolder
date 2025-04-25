import java.util.Scanner;

public class GameChoices {
GameChoices() {
    // Constructor for GameChoices
    GameLauncher gameLauncher = new GameLauncher();
    gameLauncher.playerAdd();
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
                if (gameLauncher.numPlayers != 2) {
                    System.out.println("Tic Tac Toe is a two player game, please select another game.");
                    GameChoices.main(args); // Restart the game selection
                
                }
                //calling and creating an instance of the tic tac toe class
                @SuppressWarnings("unused")
                TicTacToe tttg = new TicTacToe();
                TicTacToe.main(args);
                GameLauncher.main(args);
            }
             if (gamechosen == 3) {
                     if (GameLauncher.numPlayers == 1) {
                        Solitaire game = new Solitaire(); // Create an instance of Solitaire
                        Solitaire.main(args);
                        GameChoices.main(args); // Return to the game selection
                    } else if (gameLauncher.numPlayers >= 2) {
                        System.out.println("To many players for this game, please select another game.");
                        GameChoices.main(args); // Restart the game selection
             }
             
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
}