import java.util.*;

public class ConnectFourWithGraph {
    private static Map<String, Integer> connectFourWins = new HashMap<>();
    static final int ROWS = 6;
    static final int COLS = 7;
    static final char EMPTY = '.';
    static final char PLAYER_ONE = 'X';
    static final char PLAYER_TWO = 'O';

    char[][] board = new char[ROWS][COLS];
    Map<String, List<String>> graph = new HashMap<>();

    public ConnectFourWithGraph() {
        // Initialize board
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(board[i], EMPTY);
        }
        // Build graph (connect each cell to its valid directional neighbors)
        buildGraph();
    }

    private void buildGraph() {
        int[][] directions = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                String key = nodeKey(r, c);
                graph.putIfAbsent(key, new ArrayList<>());
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (isValid(nr, nc)) {
                        graph.get(key).add(nodeKey(nr, nc));
                    }
                }
            }
        }
    }

    private String nodeKey(int row, int col) {
        return row + "," + col;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        List<String> playerNames = gameLauncher.getPlayerNames();
        
        if (playerNames.isEmpty()) {
            playerNames = gameLauncher.playerAdd();
        }

        // Check for exactly 2 players
        if (playerNames.size() != 2) {
            System.out.println("Connect Four requires exactly 2 players!");
            System.out.println("Returning to game selection...");
            GameChoices.main(args);
            return;
        }

        ConnectFourWithGraph game = new ConnectFourWithGraph();
        game.play(playerNames, args);
    }

    public void play(List<String> playerNames, String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        String player1Name = playerNames.get(0);
        String player2Name = playerNames.get(1);

        System.out.println("\nConnect Four Game Starting!");
        System.out.println(player1Name + " will be X");
        System.out.println(player2Name + " will be O\n");

        while (playAgain) {
            // Reset board for new game
            for (int i = 0; i < ROWS; i++) {
                Arrays.fill(board[i], EMPTY);
            }

            boolean playerOneTurn = true;
            boolean gameWon = false;

            while (!gameWon) {
                printBoard();
                String currentPlayer = playerOneTurn ? player1Name : player2Name;
                System.out.print(currentPlayer + "'s turn [" + (playerOneTurn ? "X" : "O") + "], choose column (0-6): ");
                
                int col;
                try {
                    col = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter a number between 0 and 6.");
                    scanner.next();
                    continue;
                }

                if (col < 0 || col >= COLS || board[0][col] != EMPTY) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                int row = dropDisc(col, playerOneTurn ? PLAYER_ONE : PLAYER_TWO);
                char currentSymbol = playerOneTurn ? PLAYER_ONE : PLAYER_TWO;

                if (checkWinWithGraph(row, col, currentSymbol)) {
                    printBoard();
                    System.out.println(currentPlayer + " wins!");
                    connectFourWins.merge(currentPlayer, 1, Integer::sum);
                    Scoreboard.updateScore(currentPlayer, 1);
                    gameWon = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameWon = true;
                }

                playerOneTurn = !playerOneTurn;
            }

            // Ask to play again
            System.out.println("\nWould you like to:");
            System.out.println("1. Play again");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your choice (1-2): ");
            
            scanner.nextLine(); // Clear buffer
            String choice = scanner.nextLine().trim();

            if (choice.equals("2")) {
                System.out.println("\nReturning to main menu...");
                GameChoices.main(args);
                return;
            } else if (!choice.equals("1")) {
                System.out.println("\nInvalid choice. Returning to main menu...");
                GameChoices.main(args);
                return;
            }
        }
    }

    private int dropDisc(int col, char symbol) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == EMPTY) {
                board[r][col] = symbol;
                return r;
            }
        }
        return -1;
    }

    private boolean isBoardFull() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == EMPTY) return false;
        }
        return true;
    }

    private void printBoard() {
        for (char[] row : board) {
            for (char cell : row) System.out.print(cell + " ");
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    private boolean checkWinWithGraph(int startRow, int startCol, char symbol) {
        // Run DFS in 4 directions (horizontal, vertical, 2 diagonals)
        int[][] directions = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };
        for (int[] dir : directions) {
            int count = 1; // Start with the current disc
            count += dfs(startRow, startCol, dir[0], dir[1], symbol);
            count += dfs(startRow, startCol, -dir[0], -dir[1], symbol);
            if (count >= 4) return true;
        }
        return false;
    }

    private int dfs(int row, int col, int dr, int dc, char symbol) {
        int count = 0;
        int r = row + dr;
        int c = col + dc;

        while (isValid(r, c) && board[r][c] == symbol) {
            count++;
            r += dr;
            c += dc;
        }

        return count;
    }

    public static Map<String, Integer> getWins() {
        return connectFourWins;
    }
}