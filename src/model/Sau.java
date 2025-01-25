package model;

import java.util.ArrayList;
import java.util.List;

public class Sau extends Piece {

    public Sau(String colorTurn) {
        super("Sau", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Sau(String colorTurn, int x, int y) {
        super("Sau", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Sau";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        ArrayList<int[]> availableMoves = new ArrayList<>();

        // All possible one-step moves in any direction
        int[][] directions = {
                { 1, 0 }, // Down
                { -1, 0 }, // Up
                { 0, 1 }, // Right
                { 0, -1 }, // Left
                { 1, 1 }, // Down-right
                { 1, -1 }, // Down-left
                { -1, 1 }, // Up-right
                { -1, -1 } // Up-left
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Check if the move is within bounds
            if (board.isWithinBounds(newX, newY)) {
                Piece pieceAtCell = board.getPiece(newX, newY);

                // Check if the destination is empty or occupied by an opponent's piece
                if (pieceAtCell == null || !pieceAtCell.getColor().equalsIgnoreCase(this.getColor())) {
                    availableMoves.add(new int[] { newX, newY });
                }
            }
        }

        return availableMoves;
    }

}
