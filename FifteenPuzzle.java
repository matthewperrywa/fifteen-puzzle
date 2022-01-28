import java.util.Scanner;

/**
 * @author Matthew Perry
 * Description: Main class. Playable fifteen puzzle.
 */
public class FifteenPuzzle {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Grid grid = new Grid();
        // nextMove saves the user's most recent input
        char nextMove = ' ';

        System.out.println("Welcome to Fifteen Puzzle.\n");
        System.out.println("The goal is to move the tiles around, so that they are in numerical order.");
        System.out.println("A completed puzzle looks like this:\n");
        System.out.println("[01] [02] [03] [04]");
        System.out.println("[05] [06] [07] [08]");
        System.out.println("[09] [10] [11] [12]");
        System.out.println("[13] [14] [15] [  ]\n");
        System.out.println("Enter \"w\", \"a\", \"s\", \"d\" keys for up, left, down, and right movements of the blank tile.");
        System.out.println("Enter \"e\" at any time to end the game.\nGood luck!\n");

        // continues to loop until user decides to exit the program
        while (nextMove != 'e' && nextMove != 'E') {

            // continues to loop while puzzle is unsolved and user hasn't exited
            while ((grid.isSolved() == false) && (nextMove != 'e' && nextMove != 'E')) {
                // the grid is printed to show its current position
                System.out.println(grid.showGrid());
                nextMove = input.next().charAt(0);
                // the move will be executed if it is valid
                grid.move(nextMove);
            }

            String yesOrNo = "o";
            input.nextLine();

            // continues to loop until yes or no is typed. this loop is bypassed if user has decided to exit the program early
            while (((!yesOrNo.equals("yes")) && (!yesOrNo.equals("no"))) && (nextMove != 'e' && nextMove != 'E')) {
                System.out.println("You win! Want to play again? Type \"yes\" or \"no\".");
                yesOrNo = input.nextLine().toLowerCase();

                if (yesOrNo.equals("no")) {
                    nextMove = 'e';
                }
                else if (yesOrNo.equals("yes")) {
                    grid.shuffle();
                    System.out.println();
                }

            }

        }

        System.out.print("\nThanks for playing!");
        input.close();
    }
}