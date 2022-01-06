// models the physical grid for the fifteen puzzle

import java.util.ArrayList;

public class Grid {

    // order of tiles
    private int[] tiles;
    // position of the blank tile
    private int blankTile;

    // constructs a new shuffled grid
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

    // returns the current grid
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

    // shuffles grid into a random order
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

    // determines if the starting position is solvable
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

    // moves the position of the blank tile
    public void move(char wasd) {

        // up
        if (wasd == 'w' || wasd == 'W') {
            if (this.blankTile > 3) {
                tiles[this.blankTile] = tiles[this.blankTile - 4];
                this.blankTile = this.blankTile - 4;
                tiles[this.blankTile] = 0;
            }
        }

        // left
        else if (wasd == 'a' || wasd == 'A') {
            if ((this.blankTile % 4) != 0) {
                tiles[this.blankTile] = tiles[this.blankTile - 1];
                this.blankTile = this.blankTile - 1;
                tiles[this.blankTile] = 0;
            }
        }

        // down
        else if (wasd == 's' || wasd == 'S') {
            if (this.blankTile < 12) {
                tiles[this.blankTile] = tiles[this.blankTile + 4];
                this.blankTile = this.blankTile + 4;
                tiles[this.blankTile] = 0;
            }
        }

        // right
        else if (wasd == 'd' || wasd == 'D') {
            if ((this.blankTile != 3) && (this.blankTile != 7) && (this.blankTile != 11) && (this.blankTile != 15)) {
                tiles[this.blankTile] = tiles[this.blankTile + 1];
                this.blankTile = this.blankTile + 1;
                tiles[this.blankTile] = 0;
            }
        }
    }

    // determines if the puzzle is solved
    public boolean isSolved() {
        int correctSquares = 0;
        for (int i = 0; i < 15; i++) {
            if (this.tiles[i] != (i + 1)) {
                return false;
            }
        }
        return true;
    }
}