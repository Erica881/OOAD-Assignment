package Model;

public class Board {
    private Piece[][] board;
    private static final int ROWS = 8;
    private static final int COLS = 5;

    public Board() {
        this.board = new Piece[ROWS][COLS]; // 5x8 grid for the board
        // Piece order for the top (Red) and bottom (Blue) rows
        String[] pieceOrder = { "Xor", "Biz", "Sau", "Biz", "Tor" };

        // Place top row (Red) and bottom row (Blue)
        for (int row : new int[] { 0, 7 }) { // Rows 0 and 7
            String color = (row == 0) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                board[row][col] = createPiece(pieceOrder[col], color);
            }
        }

        // Swap specific pieces for the desired coordinates
        swapPieces(0, 4, 0, 0); // Swap Red Xor and Blue Xor

        // Place Ram pieces in rows 1 (Red) and 6 (Blue)
        for (int row : new int[] { 1, 6 }) { // Rows 1 and 6
            String color = (row == 1) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                board[row][col] = new Ram(color);
            }
        }

    }
    // // Place tor, biz, sau... using for loop
    // for (int i = 0; i <= 7; i += 7) {
    // board[i][0] = new Tor(i == 0 ? "Red" : "Blue"); // Ram piece at position (0,
    // 0)
    // board[i][1] = new Biz(i == 0 ? "Red" : "Blue");
    // board[i][2] = new Sau(i == 0 ? "Red" : "Blue");
    // board[i][3] = new Biz(i == 0 ? "Red" : "Blue");
    // board[i][4] = new Xor(i == 0 ? "Red" : "Blue");
    // }

    // // Place ram repeatly
    // for (int i = 1; i <= 6; i += 5) {
    // for (int j = 0; j <= 4; j++) {
    // board[i][j] = new Ram(i == 1 ? "Red" : "Blue");
    // }
    // }
    // }

    // Method to swap two pieces on the board
    private void swapPieces(int x1, int y1, int x2, int y2) {
        Piece temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
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

    public Piece getPiece(int x, int y) {
        return board[x][y]; // Return the piece at the given position
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece; // Set a piece at the given position
    }

    public void moveRam(int currentX, int currentY) {
        Piece piece = board[currentX][currentY];

        if (piece instanceof Ram) {
            Ram ram = (Ram) piece;
            ram.move(currentX, currentY, board);
        }
    }

}