import java.util.*;

public class CountryGuesserGame {
    private static Map<String, Integer> countryGuesserWins = new HashMap<>();
    private static final Map<String, Set<String>> continentCountries = new HashMap<>();
    
    static {
        continentCountries.put("South America", new HashSet<>(Arrays.asList(
            // Original 6 countries
            "Argentina", "Brazil", "Chile", "Colombia", "Peru", "Venezuela","Ecuador", "Bolivia", "Paraguay", "Uruguay", "Guyana"
        )));

        continentCountries.put("Europe", new HashSet<>(Arrays.asList(
            // Original 5 countries
            "Russia", "Germany", "France", "Turkey", "England","Italy", "Spain", "Ukraine", "Poland", "Romania"
        )));
    }

    public static void main(String[] args) {
        playGame(args);
    }

    public static void playGame(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        List<String> playerNames = gameLauncher.getPlayerNames();
        
        if (playerNames.isEmpty()) {
            playerNames = gameLauncher.playerAdd();
        }

        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);

        while (playAgain) {
            System.out.println("\nWelcome to the Country Guesser Game!");
            System.out.println("Each player will take turns guessing countries in either South America or Europe, your job is to guess 5 of the top 10 most populated countries in the continent of your choice!");
            
            Map<String, Integer> playerScores = new HashMap<>();
            for (String player : playerNames) {
                playerScores.put(player, 0);
            }
            
            Set<String> guessedCountries = new HashSet<>();
            int rounds = 5;

            // Game loop
            for (int turn = 1; turn <= rounds; turn++) {
                System.out.println("\n=== Round " + turn + " ===");
                for (String player : playerNames) {
                    System.out.println("\n" + player + "'s turn:");
                    int score = takeTurn(scanner, guessedCountries);
                    playerScores.merge(player, score, Integer::sum);
                }
            }

            // Display round results
            System.out.println("\n=== Final Scores ===");
            String winner = null;
            int highestScore = -1;
            boolean isTie = false;

            for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                
                if (entry.getValue() > highestScore) {
                    highestScore = entry.getValue();
                    winner = entry.getKey();
                    isTie = false;
                } else if (entry.getValue() == highestScore) {
                    isTie = true;
                }
            }

            if (isTie) {
                System.out.println("\n It's a tie!");
            } else {
                System.out.println("\n " + winner + " wins!");
                countryGuesserWins.merge(winner, 1, Integer::sum);
                Scoreboard.updateScore(winner, 1);
            }

            // Ask to play again
            System.out.println("\nWould you like to:");
            System.out.println("1. Play again");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your choice (1-2): ");
            
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

    private static int takeTurn(Scanner scanner, Set<String> guessedCountries) {
        List<String> continents = new ArrayList<>(continentCountries.keySet());
        for (int i = 0; i < continents.size(); i++) {
            System.out.println((i + 1) + ". " + continents.get(i));
        }

        System.out.print("Select a continent by number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // clear newline

        if (choice < 1 || choice > continents.size()) {
            System.out.println("Invalid choice. Turn skipped.");
            return 0;
        }

        String selectedContinent = continents.get(choice - 1);
        Set<String> validCountries = continentCountries.get(selectedContinent);

        System.out.print("Guess a country in " + selectedContinent + ": ");
        String guess = capitalize(scanner.nextLine().trim());

        if (guessedCountries.contains(guess)) {
            System.out.println("Already guessed! No points.");
            return 0;
        }

        if (validCountries.contains(guess)) {
            guessedCountries.add(guess);
            System.out.println("Correct!");
            return 1;
        } else {
            System.out.println("Incorrect.");
            return 0;
        }
    }

    private static String capitalize(String input) {
        String[] words = input.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1))
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static Map<String, Integer> getWins() {
        return countryGuesserWins;
    }
}
