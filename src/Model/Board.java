package Model;

// Board class to hold pieces.
class Board {
    private Piece[][] grid = new Piece[8][8];

    public Piece getPiece(int x, int y) {
        return grid[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        grid[x][y] = piece;
    }
}
