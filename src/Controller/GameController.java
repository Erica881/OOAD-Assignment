package Controller;

import Model.Board;
import Model.Piece;
import View.BoardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private Board board;
    private BoardView view;

    public GameController(Board board, BoardView view) {
        this.board = board;
        this.view = view;

        // Corrected iteration limits for 8 rows and 5 columns
        for (int i = 0; i < 8; i++) { // Loop over 8 rows
            for (int j = 0; j < 5; j++) { // Loop over 5 columns
                final int x = i;
                final int y = j;

                view.addCellListener(x, y, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(x, y);
                    }
                });
            }
        }

        // Update the board view with the current state of the board.
        updateBoardView();
    }

    private void handleCellClick(int x, int y) {
        Piece piece = board.getPiece(x, y);
        if (piece != null) {
            System.out.println(piece.getColor() + " " + piece.getName() + " selected at (" + x + ", " + y + ")");
        } else {
            System.out.println("Empty cell clicked at (" + x + ", " + y + ")");
        }
    }

    private void updateBoardView() {
        for (int i = 0; i < 8; i++) { // Loop over 8 rows
            for (int j = 0; j < 5; j++) { // Loop over 5 columns
                Piece piece = board.getPiece(i, j);
                view.updateCell(i, j, piece); // Update each cell on the view
            }
        }
    }
}
