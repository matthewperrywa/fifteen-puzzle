import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Matthew Perry
 * Description: A frame that takes the information from the grid class and puts it into a playable GUI.
 */
public class GameFrame extends JFrame implements KeyListener {

    private GamePanel[] tiles;
    private int blankTile;
    private Grid grid;

    /**
     * Description: Creates a frame that has 16 tiles and accepts keyboard input to move them around.
     * Pre-Condition: None.
     * Post-Condition: A frame containing a randomly shuffled grid of numbers is created.
     */
    public GameFrame() {

        this.tiles = new GamePanel[16];
        this.grid = new Grid();
        this.blankTile = this.grid.getBlankTile();

        this.setTitle("15 Puzzle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(510, 530);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.addKeyListener(this);

        this.setTiles();

        this.setVisible(true);

    }

    /**
     * Description: Sets the tiles in the GUI to the values of the tiles from the grid.
     * Pre-Condition: None.
     * Post-Condition: The GUI's tiles now match the order of the numbers in the grid.
     */
    private void setTiles() {
        int[] numbers = this.grid.toArray();
        for (int i = 0; i < 4; i++) {
            this.tiles[i] = new GamePanel(125 * i, 0, numbers[i]);
            this.add(this.tiles[i]);
        }
        for (int i = 4; i < 8; i++) {
            this.tiles[i] = new GamePanel(125 * (i - 4), 125, numbers[i]);
            this.add(this.tiles[i]);
        }
        for (int i = 8; i < 12; i++) {
            this.tiles[i] = new GamePanel(125 * (i - 8), 250, numbers[i]);
            this.add(this.tiles[i]);
        }
        for (int i = 12; i < 16; i++) {
            this.tiles[i] = new GamePanel(125 * (i - 12), 375, numbers[i]);
            this.add(this.tiles[i]);
        }
    }

    /**
     * Description: Swaps the position of the blank tile with the position of another tile on the GUI.
     * Pre-Condition: The given ints must both be no less than 0 and no greater than 15.
     * Post-Condition: The numbers on the GUI are swapped.
     */
    private void swap(int blankTile, int otherTile) {
        int temp = this.tiles[blankTile].getNumber();
        this.tiles[blankTile].changeLabel(this.tiles[otherTile].getNumber());
        this.tiles[otherTile].changeLabel(temp);
        this.blankTile = otherTile;
    }

    /**
     * Description: Does nothing.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Description: Does nothing.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Description: When an arrow key or WASD key is released, the blank tile will move in that direction if possible.
     * Pre-Condition: The blank tile must be moving in the direction of another tile.
     * Post-Condition: The blank tile will be swapped with the tile it is moving towards.
     *                 If the move results in a solved puzzle, a pane will pop up, notifying the user that the puzzle is solved.
     */
    public void keyReleased(KeyEvent e) {
        // up
        if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
            if (this.blankTile > 3) {
                this.swap(this.blankTile, this.blankTile - 4);
                this.grid.move('w');
            }
        }
        // left
        else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
            if ((this.blankTile % 4) != 0) {
                this.swap(this.blankTile, this.blankTile - 1);
                this.grid.move('a');
            }
        }
        // down
        else if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
            if (this.blankTile < 12) {
                this.swap(this.blankTile, this.blankTile + 4);
                this.grid.move('s');
            }
        }
        // right
        else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
            if ((this.blankTile != 3) && (this.blankTile != 7) && (this.blankTile != 11) && (this.blankTile != 15)) {
                this.swap(this.blankTile, this.blankTile + 1);
                this.grid.move('d');
            }
        }

        if (this.grid.isSolved()) {
            StartPane startPane = new StartPane(false);
            this.dispose();
        }

    }

}