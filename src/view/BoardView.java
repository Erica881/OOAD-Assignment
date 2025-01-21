// package view;

// import javax.swing.*;

// import model.Piece;

// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;
// import model.Board;
// import controller.GameController;

// public class BoardView extends JPanel {
//     private JButton[][] buttons; // 2D array of buttons to represent the board cells
//     private static final int ROWS = 8; // 8 rows
//     private static final int COLS = 5; // 5 columns
//     private static final int BUTTON_SIZE = 50; // Smaller button size for compact board
//     private List<int[]> highlightedCells = new ArrayList<>();
//     private List<ActionListener> highlightListeners = new ArrayList<>(); // Store listeners for highlighted cells
//     private Board board;
//     private GameController controller;
//     private Piece selectedPiece;
//     private int newX, newY;

//     public BoardView(GameController controller) {
//         this.controller = controller;
//         this.setLayout(new GridLayout(ROWS, COLS)); // Set layout to 8 rows and 5 columns
//         buttons = new JButton[ROWS][COLS];
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLS; j++) {
//                 buttons[i][j] = new JButton("");
//                 buttons[i][j].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE)); // Smaller size of each cell
//                 this.add(buttons[i][j]);
//             }
//         }

//         // Add padding around the board view
//         this.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // 20px padding on all sides

//     }

//     // Add a listener to a specific cell
//     public void addCellListener(int x, int y, ActionListener listener) {
//         buttons[x][y].addActionListener(listener); // Add listener to each button
//     }

//     // Get a specific cell
//     public JButton getCell(int x, int y) {
//         return buttons[x][y]; // Return the button corresponding to the (x, y) position
//     }

//     // get the selected button
//     public JButton getSelectedCell(int selectedPieceX, int selectedPieceY) {
//         return buttons[selectedPieceX][selectedPieceY];
//     }

//     // Update a cell with the piece's name and color
//     public void updateCell(int x, int y, Piece piece) {
//         JButton cell = buttons[x][y];
//         if (piece != null) {
//             cell.setIcon(piece.getImage());
//         } else {
//             cell.setIcon(null);
//         }
//     }

//     public void showAvailableMoves(List<int[]> availableMoves) {

//         // Clear previous highlights
//         clearHighlights();

//         // highlight seleectedX and Y to green
//         for (int[] move : availableMoves) {
//             int moveX = move[0];
//             int moveY = move[1];
//             buttons[moveX][moveY].setBackground(Color.GREEN);
//             // Attach a new ActionListener for this cell
//             buttons[moveX][moveY].addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     // Notify the controller about the selected move
//                     newX = moveX;
//                     newY = moveY;
//                     System.out.println("Selected move: (" + newX + ", " + newY + ")");

//                     // call controller to move the piece
//                     controller.movePiece(newX, newY, selectedPiece);

//                     // Clear highlights after the move
//                     clearHighlights();
//                 }
//             });
//         }
//     }

//     // Clear all highlights
//     public void clearHighlights() {
//         // Reset the background color of all buttons to default (can be transparent or
//         // original color)
//         for (int i = 0; i < ROWS; i++) {
//             for (int j = 0; j < COLS; j++) {
//                 buttons[i][j].setBackground(null); // Reset to the default background color
//             }
//         }

//         highlightedCells.clear();
//     }

//     // Getter for highlightedCells
//     public List<int[]> getHighlightedCells() {
//         return highlightedCells;
//     }

//     public JButton getButton(int x, int y) {
//         if (x < 0 || x >= buttons.length || y < 0 || y >= buttons[0].length) {
//             System.out.println("invalid coordinates");
//         }
//         System.out.println("Board view x: " + x + ", y: " + y);
//         return buttons[x][y];
//     }

// }

package view;

import javax.swing.*;
import model.Piece;
import controller.GameController;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BoardView extends JPanel {
    private static final int ROWS = 8, COLS = 5, BUTTON_SIZE = 50;
    private JButton[][] buttons;
    private GameController controller;

    public BoardView(GameController controller) {
        this.controller = controller;
        setLayout(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
                add(buttons[i][j]);
            }
        }
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
    }

    public void addCellListener(int x, int y, ActionListener listener) {
        buttons[x][y].addActionListener(listener);
    }

    public void updateCell(int x, int y, Piece piece) {
        JButton cell = buttons[x][y];
        if (piece != null) {
            cell.setIcon(piece.getImage());
        } else {
            cell.setIcon(null);
        }
    }

    public void showAvailableMoves(List<int[]> availableMoves) {
        clearHighlights();
        for (int[] move : availableMoves) {
            int moveX = move[0], moveY = move[1];
            buttons[moveX][moveY].setBackground(Color.GREEN);
            buttons[moveX][moveY].addActionListener(e -> {
                controller.movePiece(moveX, moveY);
                clearHighlights();
            });
        }
    }

    public void clearHighlights() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j].setBackground(null);
            }
        }
    }
}
