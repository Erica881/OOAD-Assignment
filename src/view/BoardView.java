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
