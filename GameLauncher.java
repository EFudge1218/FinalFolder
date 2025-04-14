import java.util.Scanner;

public class GameLauncher {
    private boolean running;
    
    public static void main(String[] args) {
        GameLauncher game = new GameLauncher();
        game.running = true;
        game.launcher(game.running);
    }
    /**
     * method for implimenting the main game launcher, 1 keep running
     * boolean = true will keep the code running
     * @param running
     * @return
     */
    public boolean launcher(boolean running) {
        while(this.running == true) {
            Scanner gamemenu = new Scanner(System.in);
            System.out.println("Please enter \n 1 for game selection \n 2 to exit menu");
            int menupick = gamemenu.nextInt();              
            if (menupick == 1) {
                running = true;          
            } else {
                running = false;
                this.running = false;
                gamemenu.close();
            }
        }
        
        return running;
    }
}

