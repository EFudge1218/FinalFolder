import java.util.Scanner;

public class TicTacToe {
    TicTacToe(){
        
    }
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameLauncher gameLauncher = new GameLauncher(); // Create an instance of GameLauncher

        //Game loop for "Play Again" feature
        while (true) {

            //Initialize the game board (3x3 grid)
            char[][] board = {

                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}

            };

            //Define players' symbols
            char currentPlayer = 'X';

            //Single game loop
            while (true) {

                //Display the board
                printBoard(board);

                //Prompt the current player to make a move
                System.out.println("Player " + currentPlayer + "'s turn. Enter your move (row and column: 1-3): ");
                int row = scanner.nextInt() - 1; //Convert to zero-based index
                int col = scanner.nextInt() - 1;

                //Check if the chosen cell is valid and empty
                if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {

                    System.out.println("Invalid move! Try again.");
                    continue; //Skip this step and let the player try again

                }

                //Place the player's symbol on the board
                board[row][col] = currentPlayer;

                //Check for a win or draw
                if (checkWin(board, currentPlayer)) {

                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;

                } else if (isBoardFull(board)) {

                    printBoard(board);
                    System.out.println("It's a draw!");
                    break;

                }

                //Switch to the next player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

            }

            //Ask if the user wants to play again
            System.out.println("Do you want to play again? (yes/no): ");
            scanner.nextLine();
            String playAgain = scanner.nextLine().trim().toLowerCase();

            if (!playAgain.equals("yes")) {

                System.out.println("Thanks for playing! Goodbye!");
                
                gameLauncher.launcher(true); // Return to the main menu
                

            }
        }

        

    }

    //Method to print the current state of the board
    public static void printBoard(char[][] board) {

        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {

            System.out.print("| ");
            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j] + " | ");

            }

            System.out.println();
            System.out.println("-------------");

        }
    }

    //Method to check if a player has won
    public static boolean checkWin(char[][] board, char player) {

        //Check rows
        for (int i = 0; i < 3; i++) {

            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {

                return true;

            }
        }

        //Check columns
        for (int i = 0; i < 3; i++) {

            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {

                return true;

            }
        }

        //Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {

            return true;

        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {

            return true;

        }

        //No win detected
        return false;

    }

    //Method to check if the board is full
    public static boolean isBoardFull(char[][] board) {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == ' ') {

                    return false; //Empty cell found

                }
            }
        }

        return true; //Board is full
        
    }
}