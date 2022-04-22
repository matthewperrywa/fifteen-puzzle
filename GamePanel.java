import javax.swing.*;
import java.awt.*;

/**
 * @author Matthew Perry
 * Description: Represents a single tile from the grid.
 */
public class GamePanel extends JPanel {

    private JLabel label;
    private int number;

    /**
     * Description: Creates a panel that contains a number. The x and y coordinates determine its position on the grid.
     * Pre-Condition: The number must not exceed 2 digits, as it will not fit if it is larger.
     * Post-Condition: A panel with the desired position and number is created.
     */
    public GamePanel(int x, int y, int number) {
        this.number = number;
        this.label = new JLabel();
        this.label.setFont(new Font("Mongolian Baiti", Font.BOLD, 100));
        this.changeLabel(number);
        this.setBackground(Color.white);
        this.setBounds(x, y, 125, 125);
        this.add(this.label);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Description: Changes the number displayed on the panel.
     * Pre-Condition: The number must not exceed 2 digits, as it will not fit if it is larger.
     * Post-Condition: The number is changed to the newly inputted number.
     */
    public void changeLabel(int number) {
        this.number = number;
        if (number < 1) {
            this.label.setText("  ");
        }
        else if (number < 10) {
            this.label.setText(" " + number);
        }
        else {
            this.label.setText("" + number);
        }
    }

    /**
     * Description: Returns the number of the panel.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public int getNumber() {
        return this.number;
    }

}