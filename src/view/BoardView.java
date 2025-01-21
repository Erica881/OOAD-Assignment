package view;

import javax.swing.*;
import model.Piece;
import controller.GameController;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import controller.GameController;
import model.Piece;

public class BoardView extends JPanel {
    private JButton[][] buttons; // 2D array of buttons to represent the board cells
    private JPanel gridPanel; // Panel for the board grid
    private JLabel[] rowLabels; // Labels for rows (1-8)
    private JLabel[] colLabels; // Labels for columns (a-e)
    private static final int ROWS = 8; // 8 rows
    private static final int COLS = 5; // 5 columns
    private static final int BUTTON_SIZE = 50; // Smaller button size for compact board
    private GameController controller;

    // private ArrayList<int[]> highlightedCells = new ArrayList<>();

    public BoardView(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(ROWS, COLS));
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
        for (int i = 0; i < ROWS; i++) {
            rowLabels[i] = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            rowLabelPanel.add(rowLabels[i]);
        }

        // Create column labels (a-h)
        JPanel colLabelPanel = new JPanel(new GridLayout(1, COLS));
        for (int i = 0; i < COLS; i++) {
            // colLabels[i] = new JLabel(String.valueOf((char) ('a' + i)),
            // SwingConstants.CENTER);
            colLabels[i] = new JLabel(String.valueOf(i), SwingConstants.CENTER);

            colLabelPanel.add(colLabels[i]);
        }

        // Add labels and grid to the main panel
        this.add(rowLabelPanel, BorderLayout.WEST); // Row labels on the left
        this.add(colLabelPanel, BorderLayout.NORTH); // Column labels on the top
        this.add(gridPanel, BorderLayout.CENTER); // Board grid in the center
    }

    public void addCellListener(int x, int y, ActionListener newListener) {
        // buttons[x][y].addActionListener(newListener);
        // Remove all existing listeners
        for (ActionListener listener : buttons[x][y].getActionListeners()) {
            buttons[x][y].removeActionListener(listener);
        }
        // Add the new listener
        buttons[x][y].addActionListener(newListener);
    }

    public void updateCellListener(int x, int y, ActionListener newListener) {

        // Remove all existing listeners
        for (ActionListener listener : buttons[x][y].getActionListeners()) {
            buttons[x][y].removeActionListener(listener);
        }
        // Add the new listener
        buttons[x][y].addActionListener(newListener);
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
