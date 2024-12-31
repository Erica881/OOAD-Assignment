package Model;

public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][5]; // 5x8 grid for the board

        // Place some Ram pieces for demonstration
        board[0][0] = new RamPiece(); // Ram piece at position (0, 0)
        board[7][4] = new RamPiece(); // Ram piece at position (4, 7)
    }

    public Piece getPiece(int x, int y) {
        return board[x][y]; // Return the piece at the given position
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece; // Set a piece at the given position
    }
}
