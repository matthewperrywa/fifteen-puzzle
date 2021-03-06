import java.util.ArrayList;

/**
 * @author Matthew Perry
 * Description: Grid is the physical grid for the fifteen puzzle. It is randomly generated and changes as moves are made.
 */
public class Grid {

    // order of tiles
    private int[] tiles;
    // position of the blank tile
    private int blankTile;

    /**
     * Description: Constructs a new shuffled grid.
     * Pre-Condition: None.
     * Post-Condition: The grid with its randomly shuffled starting state is created.
     */
    public Grid() {
        this.tiles = new int[16];
        this.blankTile = 0;
        this.shuffle();
    }

    /*
        grid output example:

        [01] [02] [03] [04]
        [05] [06] [07] [08]
        [09] [10] [11] [12]
        [13] [14] [15] [  ]

    */

    /**
     * Description: Returns the grid as an array of ints. A value of 0 indicates the blank space.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public int[] toArray() {
        int[] output = new int[16];
        for (int i = 0; i < 16; i++) {
            output[i] = this.tiles[i];
        }
        return output;
    }

    /**
     * Description: Returns the index of the blank tile.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public int getBlankTile() {
        return this.blankTile;
    }

    /**
     * Description: Returns the grid as a String.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public String showGrid() {
        String grid = "";
        for (int i = 0; i < 16; i+=4) {
            for (int j = 0; j < 4; j++) {
                if (this.tiles[i + j] == 0) {
                    grid += "[  ] ";
                }
                else if (this.tiles[i + j] < 10) {
                    grid += "[0" + this.tiles[i + j] + "] ";
                }
                else {
                    grid += "[" + this.tiles[i + j] + "] ";
                }
            }
            grid += "\n";
        }
        return grid;
    }

    /**
     * Description: Shuffles the grid's tiles into a random order.
     * Pre-Condition: None.
     * Post-Condition: The grid is shuffled into a new order.
     */
    public void shuffle() {
        // the grid keeps shuffling until it is in a solvable position. only half of the possible positions are solvable
        while (this.isSolvable() == false) {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            int number = 0;
            for (int i = 0; i < 16; i++) {
                numbers.add(i);
            }
            for (int k = 0; k < 16; k++) {
                    number = (int) (Math.random() * numbers.size());
                    this.tiles[k] = numbers.get(number);
                    if (numbers.get(number) == 0) {
                        this.blankTile = k;
                    }
                    numbers.remove(number);
            }
        }
    }

    /**
     * Description: Returns a boolean indicating if the grid's starting position is solvable or not.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    private boolean isSolvable () {
        // prevents the user from getting a puzzle that is already solved
        if (this.isSolved() == true) {
            return false;
        }
        // inversions are instances where a greater number appears before a lesser number
        int inversions = 0;
        // counts the number of inversions
        for (int i = 0; i < 16; i++) {
            for (int k = (i + 1); k < 16; k++) {
                if ((this.tiles[i] > this.tiles[k]) && (this.tiles[k] != 0)) {
                    inversions++;
                }
            }
        }
        // if blank tile is on an even row starting from the bottom and the inversions count is odd
        if (((this.blankTile < 4) || ((this.blankTile > 7) && (this.blankTile < 12))) && ((inversions % 2) == 1)) {
            return true;
        }
        // if the blank tile is on an odd row starting from the bottom and the inversions count is even
        else if (((this.blankTile > 11) || ((this.blankTile > 3) && (this.blankTile < 8))) && ((inversions % 2) == 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Description: Moves the position of the blank tile.
     * Pre-Condition: For the move to be valid, the char must be 'w', 'a', 's', or 'd'. Uppercase chars are fine too.
     *                Invalid moves will result in nothing happening. 'w' = up. 'a' = left. 's' = down. 'd' = right.
     * Post-Condition: If the move is valid, the blank tile is swapped with the tile in the desired direction of movement.
     */
    public void move(char wasd) {

        // move up
        if (wasd == 'w' || wasd == 'W') {
            if (this.blankTile > 3) {
                this.tiles[this.blankTile] = this.tiles[this.blankTile - 4];
                this.blankTile = this.blankTile - 4;
                this.tiles[this.blankTile] = 0;
            }
        }

        // move left
        else if (wasd == 'a' || wasd == 'A') {
            if ((this.blankTile % 4) != 0) {
                this.tiles[this.blankTile] = this.tiles[this.blankTile - 1];
                this.blankTile = this.blankTile - 1;
                this.tiles[this.blankTile] = 0;
            }
        }

        // move down
        else if (wasd == 's' || wasd == 'S') {
            if (this.blankTile < 12) {
                this.tiles[this.blankTile] = this.tiles[this.blankTile + 4];
                this.blankTile = this.blankTile + 4;
                this.tiles[this.blankTile] = 0;
            }
        }

        // move right
        else if (wasd == 'd' || wasd == 'D') {
            if ((this.blankTile != 3) && (this.blankTile != 7) && (this.blankTile != 11) && (this.blankTile != 15)) {
                this.tiles[this.blankTile] = this.tiles[this.blankTile + 1];
                this.blankTile = this.blankTile + 1;
                this.tiles[this.blankTile] = 0;
            }
        }
    }

    /**
     * Description: Determines if the puzzle is solved.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public boolean isSolved() {
        for (int i = 0; i < 15; i++) {
            if (this.tiles[i] != (i + 1)) {
                return false;
            }
        }
        return true;
    }

}