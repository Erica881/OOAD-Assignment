// package Model;

// public class Board {
//     private Piece[][] board;

//     public Board() {
//         this.board = new Piece[8][5]; // 5x8 grid for the board

//         // Place some Ram pieces for demonstration
//         board[0][0] = new Tor(Player.Red); // Ram piece at position (0, 0)
//         board[7][4] = new Tor(Player.Blue); // Ram piece at position (4, 7)
//     }

//     public Piece getPiece(int x, int y) {
//         return board[x][y]; // Return the piece at the given position
//     }

//     public void setPiece(int x, int y, Piece piece) {
//         board[x][y] = piece; // Set a piece at the given position
//     }
// }

package Model;

public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][5]; // 8x5 grid for the board

        // Initialize some pieces for demonstration
        board[0][0] = new Tor(Player.RED); // Tor for Player.RED
        board[7][4] = new Tor(Player.BLUE); // Tor for Player.BLUE
        // Add other pieces as needed
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }
}
