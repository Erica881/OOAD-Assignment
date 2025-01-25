package model;

import java.util.ArrayList;
import java.util.List;

public class Biz extends Piece {

    public Biz(String colorTurn) {
        super("Biz", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Biz(String colorTurn, int x, int y) {
        super("Biz", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Biz";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        ArrayList<int[]> availableMoves = new ArrayList<>();

        // All possible L-shape moves (3x2 or 2x3)
        // int[][] moves = {
        // { 3, 2 }, { 3, -2 }, { -3, 2 }, { -3, -2 }, // Horizontal 3x2
        // { 2, 3 }, { 2, -3 }, { -2, 3 }, { -2, -3 } // Vertical 2x3
        // };

        // All possible L-shape moves (3x2 or 2x3)
        int[][] moves = {
                { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, // Horizontal 3x2
                { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } // Vertical 2x3
        };

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];

            // Check if the move is within bounds
            if (board.isWithinBounds(newX, newY)) {
                Piece pieceAtCell = board.getPiece(newX, newY);

                // Biz can skip over other pieces, so only check the destination cell
                if (pieceAtCell == null || !pieceAtCell.getColor().equalsIgnoreCase(this.getColor())) {
                    // Add the move if the destination cell is empty or occupied by an opponent
                    availableMoves.add(new int[] { newX, newY });
                }
            }
        }

        return availableMoves;
    }

}
