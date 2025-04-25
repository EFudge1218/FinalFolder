import java.util.*;

public class Solitaire {

    public static void main (String[] args){

        //Creates card piles using individual stacks
        Stack<Stack<Card>> table = new Stack<>();
        for (int i = 0; i < 7; i++){

            table.push(new Stack<>());

        }

        //Creates stock and waste piles
        Stack<Card> stock = new Stack<>();
        Stack<Card> waste = new Stack<>();

        //Creates foundations as invidual stacks
        Stack<Stack<Card>> foundations = new Stack<>();
        for(int i = 0; i < 4; i++){

            foundations.push(new Stack<>());

        }

        //Initializes each deck and shuffles them
        Stack<Card> deck = new Stack<>();
        deck.addAll(createDeck());
        Collections.shuffle(deck);

        //Deals cards to the table piles
        dealtoTable(deck, table);

        //Adds the remaining cards to the stock
        while(!deck.isEmpty()){

            stock.push(deck.pop())

        }

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\nTable:");
            printTable(table);
            System.out.println("\nFoundations:");
            printFoundations(foundations);
            System.out.println("\nStock Size: " + stock.size());
            System.out.println("\nWaste Size: " + waste.size());

            System.out.println("\nEnter a Command (ex., 'draw', 'quit'):");
            String command = scanner.nextLine();

            if(command.equalsIgnoreCase("draw")){

                if(!stock.isEmpty()){

                    waste.push(stock.pop());

                } else{

                    System.out.println("Stock is empty!");

                }
            
            else if(command.equalsIgnoreCase("quit")){

                System.out.println("Thanks for playing!");
                break

            }

            else{

                System.out.println("Invalid Command");

            }

            }

        scanner.close();

        }

    //Method for creating a deck of cards
    public static List<Card> createDeck(){

        List<Card> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] cvs = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String suit : suits){

            for(String cv : cvs){

                deck.add(new Card(cv, suit));

            }

        }

        return deck;

        }

    //Method that deals cards to the table piles
    public static void dealToTable(Stack<Card> deck, Stack<Stack<Card>> table){

        for(int i = 0; i < table.size(); i ++){

            Stack<Card> currentPile = table.get(i);
            for(int j = 0; j <= i; j++){

                currentPile.push(deck.pop());

                }

            }

        }

        //Method to print the table piles
        public static void printTable(Stack<Stack<Card>> table){

            for(int i = 0; i < table.size(); i++){

                for(Card card : table.get(i)){

                    System.out.print(card + " ");

                }

                System.out.println();

            }

        }

        //Method to print the foundation piles
        public static void printFoundations(Stack<Stack<Card>> foundations){

            for(int i = 0; i < foundations.size(); i++){

                System.out.print("Foundation " + (i + 1) + ": ");
                for(Card card : foundations.get(i)){

                    System.out.print(card + " ");

                }

                System.out.println();

            }

        }
 
    }

    //Class to represent a card
    class Card{

        private String cv;
        private String suit;

        public Card(String cv, String suit){

            this.cv = cv;
            this.suit = suit;

        }

        public String toString(){

            return cv + " of " + suit;

        }

    }

}