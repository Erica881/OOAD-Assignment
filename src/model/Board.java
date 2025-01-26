package model;

import java.util.HashMap;
import java.util.Map;

interface PieceFactory {
    Piece create(String color, int x, int y);
}

public class Board {
    private Piece[][] board;
    // private boolean isGamePaused;
    private static final int ROWS = 8;
    private static final int COLS = 5;
    private boolean isFlipped = false;
    boolean isBlueSauAlive = false;
    boolean isRedSauAlive = false;
    boolean isPieceCapture = false;
    Piece capturedPiece = null;

    public Board() {
        this.board = new Piece[ROWS][COLS]; // 5x8 grid for the board
        initialize(); // Call initialize() to set up the board
    }

    // Initialize the board with pieces
    public void initialize() {
        // Clear the board
        isFlipped = false;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = null; // clear all positions
            }
        }

        // Piece order for the top (Red) and bottom (Blue) rows
        String[] pieceOrder = { "Xor", "Biz", "Sau", "Biz", "Tor" };

        // Create a HashMap to map piece types to their creation logic
        Map<String, PieceFactory> pieceFactoryMap = new HashMap<>();
        pieceFactoryMap.put("Xor", (c, x, y) -> new Xor(c, x, y));
        pieceFactoryMap.put("Biz", (c, x, y) -> new Biz(c, x, y));
        pieceFactoryMap.put("Sau", (c, x, y) -> new Sau(c, x, y));
        pieceFactoryMap.put("Tor", (c, x, y) -> new Tor(c, x, y));
        pieceFactoryMap.put("Ram", (c, x, y) -> new Ram(c, x, y));

        // Place top row (Red) and bottom row (Blue)
        for (int row : new int[] { 0, 7 }) { // Rows 0 (Red) and 7 (Blue)
            String color = (row == 0) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                String pieceType = pieceOrder[col];
                PieceFactory factory = pieceFactoryMap.get(pieceType);

                if (factory != null) {
                    int adjustCol = (color.equals("Red")) ? COLS - 1 - col : col; // Flip Red's column order
                    board[row][adjustCol] = factory.create(color, row, adjustCol); // Create and place the piece
                } else {
                    System.out.println("Invalid piece type: " + pieceType);
                }
            }

        }

        // Place Ram pieces in rows 1 (Red) and 6 (Blue)
        for (int row : new int[] { 1, 6 }) { // Rows 1 and 6
            String color = (row == 1) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                board[row][col] = pieceFactoryMap.get("Ram").create(color, row, col);
            }
        }
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flipBoard() {
        isFlipped = !isFlipped;
    }

    public int[] mapViewToBoardCoordinates(int x, int y) {
        if (isFlipped()) {
            // Translate the view coordinates to the flipped board's coordinates
            int flippedX = ROWS - 1 - x;
            int flippedY = COLS - 1 - y;
            return new int[] { flippedX, flippedY };
        }
        // Return the original coordinates if the board is not flipped
        return new int[] { x, y };
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

    public void transformTor() {
        System.out.println("Transforming Tor pieces to Xor...");
        // Iterate over the board and transform each Tor piece
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof Tor) {
                    // Replace the Tor piece with an Xor piece of the same color
                    setPiece(row, col, new Xor(piece.getColor(), row, col));
                    System.out.println("Transformed Tor at position (" + row + ", " + col + ") to Xor.");
                }
            }
        }
    }

    public void transformTorXor() {
        // Create a HashMap to map piece types to their transformation logic
        Map<Class<? extends Piece>, String> transformationMap = new HashMap<>();
        transformationMap.put(Tor.class, "Xor");
        transformationMap.put(Xor.class, "Tor");

        // PieceFactory map for creating pieces
        Map<String, PieceFactory> pieceFactoryMap = new HashMap<>();
        pieceFactoryMap.put("Xor", (color, x, y) -> new Xor(color, x, y));
        pieceFactoryMap.put("Tor", (color, x, y) -> new Tor(color, x, y));

        // Iterate over the board and perform transformations
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Piece piece = getPiece(row, col);
                if (piece != null && transformationMap.containsKey(piece.getClass())) {
                    // Get the target piece type from the transformation map
                    String targetPieceType = transformationMap.get(piece.getClass());
                    PieceFactory factory = pieceFactoryMap.get(targetPieceType);
                    // Replace the current piece with the transformed piece
                    setPiece(row, col, factory.create(piece.getColor(), row, col));

                }
            }
        }
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 5; // Update board dimensions as needed
    }

    public String formatCoordinate(int x, int y) {
        char columnLetter = (char) ('a' + y); // Adjust
        // column for flipped board
        int rowNumber = x + 1; // Adjust row number for flipped board
        return "(" + rowNumber + ", " + columnLetter + ")";
    }

    public void movePiece(int toX, int toY, Piece fromPiece) {
        int fromX = fromPiece.getX();
        int fromY = fromPiece.getY();

        // Check if there is an enemy piece at the target position
        Piece targetPiece = board[toX][toY];
        if (targetPiece != null) {
            if (!targetPiece.getColor().equals(fromPiece.getColor())) {
                // Capture the enemy piece
                isPieceCapture = true;
                capturedPiece = targetPiece;
                board[toX][toY] = null; // Remove the enemy piece from the board
            } else {
                // Invalid move: cannot capture your own piece
                System.out.println("Invalid move: Target position contains your own piece!");
                return;
            }
        }

        // Remove the piece from its old position
        board[fromX][fromY] = null;

        // Update the piece's position
        fromPiece.setX(toX);
        fromPiece.setY(toY);

        // Add the piece to its new position
        board[toX][toY] = fromPiece;

    }

    public boolean isPieceCapture() {
        return isPieceCapture;
    }

    public Piece getCapturePiece() {
        return capturedPiece;
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

}
