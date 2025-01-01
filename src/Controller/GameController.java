// package Controller;

// import Model.Board;
// import Model.Player;
// import Model.Piece;
// import View.BoardView;

// public class GameController {
//     private Board board;
//     private BoardView view;
//     private Player currentPlayer; // Track whose turn it is

//     public GameController(Board board, BoardView view) {
//         this.board = board;
//         this.view = view;
//         this.currentPlayer = Player.RED; // Start with player RED

//         // Add listeners to the buttons to handle user input
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 5; j++) {
//                 final int x = i;
//                 final int y = j;

//                 view.addCellListener(x, y, e -> handleCellClick(x, y));
//             }
//         }

//         // Update the board view at the beginning of the game
//         updateBoardView();
//     }

//     private void handleCellClick(int x, int y) {
//         Piece piece = board.getPiece(x, y);
//         if (piece != null) {
//             // Ensure that the current player can only select their own pieces
//             if (piece.getColor() == currentPlayer) {
//                 System.out.println(piece.getType() + " " + piece.getColor() + " selected at (" + x + ", " + y + ")");
//                 // Allow piece movement logic to be added here
//             } else {
//                 System.out.println("It's not your turn or you cannot move this piece.");
//             }
//         } else {
//             System.out.println("Empty cell clicked at (" + x + ", " + y + ")");
//         }
//     }

//     // Switch the turn to the next player
//     private void switchTurn() {
//         currentPlayer = currentPlayer.next();
//         System.out.println("It's now " + currentPlayer + "'s turn.");
//     }

//     private void updateBoardView() {
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 5; j++) {
//                 Piece piece = board.getPiece(i, j);
//                 view.updateCell(i, j, piece); // Update each cell on the view
//             }
//         }
//     }

//     public void makeMove(int fromX, int fromY, int toX, int toY) {
//         Piece piece = board.getPiece(fromX, fromY);
//         if (piece != null && piece.getColor() == currentPlayer) {
//             // Validate move logic here (e.g., piece type, destination cell, etc.)
//             board.setPiece(toX, toY, piece); // Move piece to new position
//             board.setPiece(fromX, fromY, null); // Clear the old position

//             updateBoardView(); // Refresh the view after the move
//             switchTurn(); // Switch turn after a successful move
//         }
//     }
// }

package Controller;

import Model.Board;
import Model.Piece;
import Model.Player;
import View.BoardView;

public class GameController {
    private Board board;
    private BoardView view;
    private boolean isPlayerOneTurn = true;
    private Player currentPlayer;

    public GameController(Board board, BoardView view) {
        this.board = board;
        this.view = view;
        initializeBoard();
    }

    private void initializeBoard() {
        // Set up initial pieces on the board and update the view
        // for (int i = 0; i < 8; i++) {
        // for (int j = 0; j < 5; j++) {
        // Piece piece = board.getPiece(i, j);
        // view.updateCell(i, j, piece);
        // }
        // }
        // view.addCellListener(x, y, e -> handleCellClick(x, y));

        // Add listeners to the buttons to handle user input
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                final int x = i;
                final int y = j;

                view.addCellListener(x, y, e -> handleCellClick(x, y));
            }
        }

        // Update the board view at the beginning of the game
        updateBoardView();
    }

    public void switchTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;
        view.flipBoard(); // Flip the board when switching turns
    }

    private void updateBoardView() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Piece piece = board.getPiece(i, j);
                view.updateCell(i, j, piece); // Update each cell on the view
            }
        }
    }

    private void handleCellClick(int x, int y) {
        Piece piece = board.getPiece(x, y);
        if (piece != null) {
            // Ensure that the current player can only select their own pieces
            if (piece.getColor() == currentPlayer) {
                System.out.println(piece.getType() + " " + piece.getColor() + " selected at (" + x + ", " + y + ")");
                // Allow piece movement logic to be added here
            } else {
                System.out.println("It's not your turn or you cannot move this piece.");
            }
        } else {
            System.out.println("Empty cell clicked at (" + x + ", " + y + ")");
        }
    }
}
