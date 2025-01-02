package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Model.Piece;

public class BoardView extends JPanel {
    private JButton[][] buttons; // 2D array of buttons to represent the board cells
    private static final int ROWS = 8; // 8 rows
    private static final int COLS = 5; // 5 columns

    public BoardView() {
        this.setLayout(new GridLayout(ROWS, COLS)); // Set layout to 8 rows and 5 columns
        buttons = new JButton[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setPreferredSize(new Dimension(60, 60)); // Size of each cell
                this.add(buttons[i][j]);
            }
        }
    }

    // Add a listener to a specific cell
    public void addCellListener(int x, int y, ActionListener listener) {
        buttons[x][y].addActionListener(listener); // Add listener to each button
    }

    // Get a specific cell
    public JButton getCell(int x, int y) {
        return buttons[x][y]; // Return the button corresponding to the (x, y) position
    }

    // Update a cell with the piece's name and color
    public void updateCell(int x, int y, Piece piece) {
        if (piece != null) {
            // Update the cell with the piece's information (e.g., name or color)
            buttons[x][y].setText(piece.getName() + " (" + piece.getColor() + ")");
        } else {
            // Clear the cell if it's empty
            buttons[x][y].setText("");
        }
    }
}