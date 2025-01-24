package view;

import javax.swing.*;

import model.Piece;

import java.awt.*;
import java.awt.event.ActionListener;
// import java.util.ArrayList;
import java.util.ArrayList;

public class BoardView extends JPanel {
    private JButton[][] buttons; // 2D array of buttons to represent the board cells
    private JPanel gridPanel; // Panel for the board grid
    private JLabel[] rowLabels; // Labels for rows (1-8)
    private JLabel[] colLabels; // Labels for columns (a-e)
    private static final int ROWS = 8; // 8 rows
    private static final int COLS = 5; // 5 columns
    private static final int BUTTON_SIZE = 50; // Smaller button size for compact board
    // private ArrayList<int[]> highlightedCells = new ArrayList<>();

    public BoardView() {
        this.setLayout(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(ROWS, COLS)); // Set layout to 8 rows and 5 columns
        buttons = new JButton[ROWS][COLS];
        rowLabels = new JLabel[ROWS];
        colLabels = new JLabel[COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE)); // Smaller size of each cell
                gridPanel.add(buttons[i][j]);

            }
        }
        // Add padding around the board view
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20px padding on all sides

         // Create row labels (1-8)
         JPanel rowLabelPanel = new JPanel(new GridLayout(ROWS, 1));
         for (int i = 0; i < ROWS ; i++) {
             rowLabels[i] = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
             rowLabelPanel.add(rowLabels[i]);
         }
 
         // Create column labels (a-h)
         JPanel colLabelPanel = new JPanel(new GridLayout(1, COLS));
         for (int i = 0; i < COLS; i++) {
             colLabels[i] = new JLabel(String.valueOf((char) ('a' + i)), SwingConstants.CENTER);
             colLabelPanel.add(colLabels[i]);
         }
 
         // Add labels and grid to the main panel
         this.add(rowLabelPanel, BorderLayout.WEST); // Row labels on the left
         this.add(colLabelPanel, BorderLayout.NORTH); // Column labels on the top
         this.add(gridPanel, BorderLayout.CENTER); // Board grid in the center
    }
    
    public void flipBoardView() {
        // Flip row labels (invert order)
        for (int i = 0; i < rowLabels.length / 2; i++) {
            String temp = rowLabels[i].getText();
            rowLabels[i].setText(rowLabels[rowLabels.length - 1 - i].getText());
            rowLabels[rowLabels.length - 1 - i].setText(temp);
        }

        // Reverse column labels (a-e -> e-a or vice versa)
        for (int i = 0; i < colLabels.length / 2; i++) {
            String temp = colLabels[i].getText();
            colLabels[i].setText(colLabels[colLabels.length - 1 - i].getText());
            colLabels[colLabels.length - 1 - i].setText(temp);
        }
    
        // Refresh the grid panel
        gridPanel.revalidate();
        gridPanel.repaint();
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
        JButton cell = buttons[x][y];
        if (piece != null) {
            cell.setIcon(piece.getImage());
        } else {
            cell.setIcon(null);
        }
    }

    public void highlightAvailableMoves(ArrayList<int[]> availableMoves) {
        for (int[] move : availableMoves) {
            int x = move[0];
            int y = move[1];
            buttons[x][y].setBackground(Color.YELLOW); // Highlight the valid cells
        }
    }

    public void clearHighlights() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setBackground(null); // Reset cell background
            }
        }
    }
    
    public void addMoveSelectionListener(MoveSelectionListener listener) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                for (ActionListener al : buttons[i][j].getActionListeners()) {
                    buttons[i][j].removeActionListener(al);
                }
                int x = i;
                int y = j;
                buttons[x][y].addActionListener(e -> {
                    listener.onMoveSelected(x, y); // Notify the listener
                });
            }
        }
    }

    @FunctionalInterface
    public interface MoveSelectionListener {
        void onMoveSelected(int targetX, int targetY);
    }

    // public void highlightAvailableMoves(ArrayList<int[]> availableMoves) {
    // // First clear previous highlights
    // clearHighlights();

    // // Store the highlighted cells for later use
    // highlightedCells = availableMoves;

    // // Change the background color of the buttons for the available moves
    // for (int[] position : highlightedCells) {
    // int x = position[0];
    // int y = position[1];
    // buttons[x][y].setBackground(Color.YELLOW); // Set background color to yellow
    // for highlighting
    // }
    // }

    // // Clear all highlights
    // public void clearHighlights() {
    // // Reset the background color of all buttons to default (can be transparent
    // or
    // // original color)
    // for (int i = 0; i < ROWS; i++) {
    // for (int j = 0; j < COLS; j++) {
    // buttons[i][j].setBackground(null); // Reset to the default background color
    // }
    // }

    // highlightedCells.clear();
    // }
}