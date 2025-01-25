package model;

import java.util.ArrayList;

public class Tor extends Piece {
    
    public Tor(String colorTurn) {
        super("Tor", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Tor(String colorTurn, int x, int y) {
        super("Tor", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Tor";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        ArrayList<int[]> availableMoves = new ArrayList<>();

        // Check moves in four orthogonal directions (up, down, left, right)
        availableMoves.addAll(getOrthogonalMoves(x, y, board));

        return availableMoves;
    }

    // Function to get all orthogonal moves (up, down, left, right) for Tor
    private ArrayList<int[]> getOrthogonalMoves(int x, int y, Board board) {
        ArrayList<int[]> moves = new ArrayList<>();

        // Check all four directions
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Up, Down, Left, Right

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            // Continue in the direction until a piece is encountered or the board edge is
            // reached
            int i = 1;
            while (true) {
                int newX = x + i * dx;
                int newY = y + i * dy;

                // If new position is within bounds
                if (newX >= 0 && newX < board.getBoard().length && newY >= 0 && newY < board.getBoard()[0].length) {
                    // If there's a piece, the Tor cannot skip over it
                    Piece pieceAtNewPos = board.getPiece(newX, newY);
                    if (pieceAtNewPos == null || !pieceAtNewPos.getColor().equals(this.getColor())) {
                        moves.add(new int[] { newX, newY });
                    }
                    if (pieceAtNewPos != null)
                        break; // Stop if the piece is not empty
                } else {
                    break; // Stop if out of bounds
                }

                i++; // Move further in the current direction
            }
        }

        return moves;
    }

}