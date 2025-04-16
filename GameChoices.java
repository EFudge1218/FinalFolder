import java.util.Scanner;

public class GameChoices {
    GameChoices() {
        // Constructor for GameChoices
    }

    public static void main(String[] args) {
        try (Scanner gameselect = new Scanner(System.in)) {
            System.out.println(
                    "Please select Game \n 1 for  \n 2 for  \n 3 for  \n 4 to return to Main Menu \n 5 to quit");
            int gamechosen = gameselect.nextInt();
            if (gamechosen == 1) {
                GameOne.main(args); // main method of GameOne
                return;
            }
            // if (gamechosen == 2) {
            // gametwo();
            // }
            // if (gamechosen == 3) {
            // gamethree();
            // }
            if (gamechosen == 4) {
                System.out.println("Returning to main menu");
                GameLauncher.main(args); // Return to the main menu

            }
            if (gamechosen == 5) {
                System.out.println("Thank you for playing, goodbye!");
                GameLauncher gameLauncher = new GameLauncher();
                gameLauncher.launcher(false); // Exit the game
                return;
            } else {
                System.out.println("Invalid choice, please try again.");
                main(args); // Restart the game selection

            }
        }
    }
}