// package View;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionListener;
// import Model.Piece;

// public class BoardView extends JPanel {
//     private JButton[][] buttons;
//     private static final int ROWS = 8;
//     private static final int COLS = 5;

//     public BoardView() {
//         this.setLayout(new GridLayout(ROWS, COLS));
//         buttons = new JButton[ROWS][COLS];
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLS; j++) {
//                 buttons[i][j] = new JButton("");
//                 buttons[i][j].setPreferredSize(new Dimension(60, 60));
//                 this.add(buttons[i][j]);
//             }
//         }
//     }

//     public void addCellListener(int x, int y, ActionListener listener) {
//         buttons[x][y].addActionListener(listener);
//     }

//     public void updateCell(int x, int y, Piece piece) {
//         JButton cell = buttons[x][y];
//         if (piece != null) {
//             // Load the image and scale it to the button size
//             ImageIcon icon = piece.getImage();
//             if (icon != null) {
//                 Image scaledImage = icon.getImage().getScaledInstance(
//                         cell.getWidth(), cell.getHeight(), Image.SCALE_SMOOTH);
//                 cell.setIcon(new ImageIcon(scaledImage));
//             }
//             cell.setText(""); // Clear any text
//         } else {
//             cell.setIcon(null); // Clear the icon if no piece is present
//             cell.setText("");
//         }
//     }

// }

package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Model.Piece;

public class BoardView extends JPanel {
    private JButton[][] buttons;
    private static final int ROWS = 8;
    private static final int COLS = 5;
    private boolean isFlipped = false;

    public BoardView() {
        this.setLayout(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                this.add(buttons[i][j]);
            }
        }
    }

    public void addCellListener(int x, int y, ActionListener listener) {
        buttons[x][y].addActionListener(listener);
    }

    public void updateCell(int x, int y, Piece piece) {
        JButton cell = buttons[x][y];
        if (piece != null) {
            cell.setIcon(piece.getImage());
            cell.setText("");
        } else {
            cell.setIcon(null);
            cell.setText("");
        }
    }

    public void flipBoard() {
        this.removeAll(); // Remove all components
        isFlipped = !isFlipped; // Toggle the flipped state

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int flippedRow = isFlipped ? ROWS - 1 - i : i;
                int flippedCol = isFlipped ? COLS - 1 - j : j;
                this.add(buttons[flippedRow][flippedCol]);
            }
        }

        this.revalidate();
        this.repaint();
    }
}
