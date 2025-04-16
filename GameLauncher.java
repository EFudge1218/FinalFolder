import java.util.Scanner;

public class GameLauncher {
    GameLauncher() {
        // Constructor for GameLauncher
    }
    boolean running;
    
    public static void main(String[] args) {
        GameLauncher game = new GameLauncher();
        game.running = true;
        game.launcher(game.running);
    }
    /**
     * method for implimenting the main game launcher, 1 will keep running
     * boolean = true w
     * 2 = false and exits the code
     * @param running
     * @return
     */
    public boolean launcher(boolean running) {
        Scanner gamemenu = new Scanner(System.in);
        try {
            while(this.running == true) {
                System.out.println("Please enter \n 1 for game selection \n 2 to exit menu");
                int menupick = gamemenu.nextInt();              
                if (menupick == 1) {
                    @SuppressWarnings("unused")
                    GameChoices gameChoices = new GameChoices(); // Create an instance of GameChoices.
                    GameChoices.main(null); // Call the main method of GameChoices
                              
                } else {
                    running = false;
                    this.running = false;
                }
            }
        } finally {
            gamemenu.close();
        }
        
        return running;
    }
}

