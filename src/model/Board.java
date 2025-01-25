package model;

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

        // Place top row (Red) and bottom row (Blue)
        for (int row : new int[] { 0, 7 }) { // Rows 0 and 7
            String color = (row == 0) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                int adjustCol = color.equals("Red") ? COLS - 1 - col : col;
                board[row][adjustCol] = createPiece(pieceOrder[col], color, row, adjustCol);

            }
        }

        // Place Ram pieces in rows 1 (Red) and 6 (Blue)
        for (int row : new int[] { 1, 6 }) { // Rows 1 and 6
            String color = (row == 1) ? "Red" : "Blue";
            for (int col = 0; col < COLS; col++) {
                board[row][col] = new Ram(color, row, col);
            }
        }
    }

    private Piece createPiece(String type, String color, int x, int y) {
        return switch (type) {
            case "Tor" -> new Tor(color, x, y);
            case "Biz" -> new Biz(color, x, y);
            case "Sau" -> new Sau(color, x, y);
            case "Xor" -> new Xor(color, x, y);
            default -> throw new IllegalArgumentException("Invalid piece type: " + type);
        };
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

        // Iterate over the board and perform the transformations
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof Tor) {
                    // Transform Tor to Xor
                    setPiece(row, col, new Xor(piece.getColor(), row, col));
                } else if (piece instanceof Xor) {
                    // Transform Xor to Tor
                    setPiece(row, col, new Tor(piece.getColor(), row, col));
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
                System.out.println("Captured " + targetPiece.getName() + " at (" + toX + ", " + toY + ")");
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
