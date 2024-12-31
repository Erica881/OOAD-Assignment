package View;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame {
    private JButton[][] cells = new JButton[8][8];

    public BoardView() {
        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        // Initialize buttons for the board.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                add(cells[i][j]);
            }
        }
    }

    public JButton getCell(int x, int y) {
        return cells[x][y];
    }

    // Update cell display with piece name.
    public void updateCell(int x, int y, String pieceName) {
        cells[x][y].setText(pieceName);
    }
}
