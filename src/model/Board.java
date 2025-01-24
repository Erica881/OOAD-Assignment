package model;

public class Board {
    private Piece[][] board;
    // private boolean isGamePaused;
    private static final int ROWS = 8;
    private static final int COLS = 5;
    private boolean Flipped = false;

    public Board() {
        this.board = new Piece[ROWS][COLS]; // 5x8 grid for the board
        initialize(); // Call initialize() to set up the board
    }

    // Initialize the board with pieces
    public void initialize() {
        // Clear the board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = null; // clear all positions
            }
        }

        // Piece order for the top (Red) and bottom (Blue) rows
        String[] pieceOrder = { "Xor", "Biz", "Sau", "Biz", "Tor" };

        // Place top row (Red) and bottom row (Blue)
        for (int row : new int[] { 0, 7 }) { // Rows 0 and 7
            String color = (row == 0) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                int adjustCol = color.equals("Red") ? COLS - 1 - col : col;
                board[row][adjustCol] = createPiece(pieceOrder[col], color);
            }
        }

        // Swap specific pieces for the desired coordinates
        //swapPieces(0, 4, 0, 0); // Swap Red Xor and Blue Xor

        // Place Ram pieces in rows 1 (Red) and 6 (Blue)
        for (int row : new int[] { 1, 6 }) { // Rows 1 and 6
            String color = (row == 1) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                board[row][col] = new Ram(color);
            }
        }
    }

    private Piece createPiece(String type, String color) {
        return switch (type) {
            case "Tor" -> new Tor(color);
            case "Biz" -> new Biz(color);
            case "Sau" -> new Sau(color);
            case "Xor" -> new Xor(color);
            default -> throw new IllegalArgumentException("Invalid piece type: " + type);
        };
    }

    public boolean isFlipped() {
        return Flipped;
    }
    
    public void setFlipped(boolean flipped) {
        this.Flipped = flipped;
    }

    public void flipBoard() {
        int row = board.length;

        // Reverse the rows in the board
        for (int i = 0; i < row / 2; i++) {
            Piece[] temp = board[i];
            board[i] = board[row - 1 - i];
            board[row - 1 - i] = temp;
        }

        // Reverse the columns to flip horizontally
        for (int i = 0; i < row; i++) { // Iterate over each row
            for (int j = 0; j < COLS / 2; j++) { // Reverse columns for the row
                Piece temp = board[i][j];
                board[i][j] = board[i][COLS - 1 - j];
                board[i][COLS - 1 - j] = temp;
            }
        }

        Flipped = !Flipped;
    }


    public Piece getPiece(int x, int y) {
        return board[x][y]; // Return the piece at the given position
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece; // Set a piece at the given position
    }

    public Piece[][] getBoard() {
        return board; // Return the 2D array representing the board
    }

    public void movePiece(int x, int y) {
        Piece piece = board[x][y];

        if (piece != null) {
            piece.move(x, y, this); // Let the piece handle its own movement
        }
    }

}
