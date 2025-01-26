package model;

import java.util.ArrayList;

public class Ram extends Piece {
    private boolean moveBack = false;

    public Ram(String colorTurn, int x, int y) {
        super("Ram", colorTurn, x, y);
    }

    public void movingBack() {
        moveBack = !moveBack;
        this.rotateImage();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        ArrayList<int[]> availableMoves = new ArrayList<>();

        // Compute the initial direction, accounting for movement state and board orientation
        int initialDirection = (moveBack ? 1 : -1) * (board.isFlipped() ? -1 : 1);

        // Generate a stream of potential moves
        java.util.stream.Stream.of(initialDirection, -initialDirection) // Forward and reverse directions
            .map(direction -> x + direction) // Calculate new positions
            .filter(newX -> isMoveValid(newX, y, board)) // Filter valid moves
            .findFirst() // Take the first valid move
            .ifPresent(newX -> {
                availableMoves.add(new int[]{newX, y}); // Add the move to the list
            });

        return availableMoves;
    }

    private boolean isMoveValid(int newX, int y, Board board) {
        if (!board.isWithinBounds(newX, y)) {
            movingBack();
            return false; // Out of bounds, direction changed
        }

        Piece targetPiece = board.getPiece(newX, y);
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            movingBack(); // Same color piece encountered, change direction
            return false;
        }

        return true; // Valid move: within bounds and not blocked by the same color piece
    }

    
}
