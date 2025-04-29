import java.util.*;

public class CountryGuesserGame {
    // Map of continent names, and sets of countries
    private static final Map<String, Set<String>> continentCountries = new HashMap<>();
    // Static block to list the  countries for each continent
    static {
        continentCountries.put("South America", new HashSet<>(Arrays.asList(
            "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
            "Paraguay", "Peru", "Uruguay", "Venezuela"
        )));

        continentCountries.put("Europe", new HashSet<>(Arrays.asList(
            "Albania", "Andorra", "Armenia", "Austria", "Azerbaijan", "Belarus", "Belgium",
            "Bosnia", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia",
            "Finland", "France", "Georgia", "Germany", "Greece", "Hungary", "Iceland", "Ireland",
            "Italy", "Kosovo", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta",
            "Moldova", "Monaco", "Netherlands", "Norway", "Poland", "Portugal", "Romania",
            "Russia", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland",
            "Turkey", "Ukraine", "United Kingdom"
        )));
    }

    // Main
    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        List<String> playerNames = gameLauncher.getPlayerNames();
        
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> playerScores = new HashMap<>();
        
        // Initialize scores for each player
        for (String player : playerNames) {
            playerScores.put(player, 0);
        }
        
        Set<String> guessedCountries = new HashSet<>();
        int rounds = 5; // Number of turns each player gets

        System.out.println("Welcome to the Country Guesser Game!");

        // Game loop
        for (int turn = 1; turn <= rounds; turn++) {
            for (String player : playerNames) {
                System.out.println("\nTurn " + turn + " - " + player + ":");
                int score = takeTurn(scanner, guessedCountries);
                playerScores.merge(player, score, Integer::sum);
            }
        }

        // Display final results
        System.out.println("\n--- Final Scores ---");
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
            System.out.println("🤝 It's a tie!");
        } else {
            System.out.println("🏆 " + winner + " wins!");
            // Update the main scoreboard
            Scoreboard.updateScore(winner, 1);
        }

        scanner.close();
        gameLauncher.launcher(true); // Return to main menu
    }

    // This uses a single player's turn
    private static int takeTurn(Scanner scanner, Set<String> guessedCountries) {
        // Show the available continents from the map
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

        // Check if guess was already made
        if (guessedCountries.contains(guess)) {
            System.out.println("Already guessed! No points.");
            return 0;
        }

        // Check if guess is correct
        if (validCountries.contains(guess)) {
            guessedCountries.add(guess);
            System.out.println("Correct!");
            return 1;
        } else {
            System.out.println("Incorrect.");
            return 0;
        }
    }

    // Capitalizes the first letter of each word
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
}
