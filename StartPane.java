import javax.swing.*;

/**
 * @author Matthew Perry
 * Description: An option pane that is displayed before the user plays the game.
 */
public class StartPane extends JOptionPane {

    /**
     * Description: Creates a window that is shown before the user plays the game.
     *              If it is the start screen, the rules and instructions will be displayed.
     *              If it is not the start screen, it will act as a win screen asking the user if they want to play again.
     * Pre-Condition: None.
     * Post-Condition: If the user clicks "Yes" and decides to play, a frame with the game in it will open up.
     */
    public StartPane(boolean isStartScreen) {

        // buttonPressed is the value of the button that the user will click
        int buttonPressed = 2;

        // the opening screen of the game
        if (isStartScreen == true) {
            buttonPressed = this.showConfirmDialog(null, "Welcome to 15 Puzzle!\n\nA completed puzzle looks like this:\n\n[ 1 ] [ 2 ] [ 3 ] [ 4 ]\n[ 5 ] [ 6 ] [ 7 ] [ 8 ]\n[ 9 ] [10] [11] [12]\n[13] [14] [15] [    ]\n\nUse WASD or arrow keys to move the empty tile.\n\nDo you want to play?", "15 Puzzle", JOptionPane.YES_NO_OPTION);
        }
        // screen after user solves the puzzle
        else {
            buttonPressed = this.showConfirmDialog(null, "You win!\n\nDo you want to play again?", "15 Puzzle", JOptionPane.YES_NO_OPTION);
        }

        // a new game will open if the user decides to play
        if (buttonPressed == 0) {
            GameFrame gameFrame = new GameFrame();
        }

    }

}