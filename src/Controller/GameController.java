package Controller;

import View.BoardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private Board board;
    private BoardView view;

    public GameController(Board board, BoardView view) {
        this.board = board;
        this.view = view;

        // Add listeners to buttons for user interaction.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int x = i;
                final int y = j;

                view.getCell(x, y).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(x, y);
                    }
                });
            }
        }
    }

    // Handle a click on a cell.
    private void handleCellClick(int x, int y) {
        Piece piece = board.getPiece(x, y);
        if (piece != null) {
            System.out.println(piece.getColor() + " " + piece.getName() + " selected at (" + x + ", " + y + ")");
        } else {
            System.out.println("Empty cell clicked at (" + x + ", " + y + ")");
        }
    }
}
