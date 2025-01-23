package model;

import java.util.ArrayList;

public class Sau extends Piece {

    public Sau(String colorTurn) {
        super("Sau", colorTurn); // Hardcoded color "Gold" for the Tor piece
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
    public ArrayList<int[]> getAvailableMoves(int currentX, int currentY, Board board) {
        ArrayList<int[]> moves = new ArrayList<>();

        // Sau can move one step in any direction
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // Row offsets for all 8 directions
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1}; // Column offsets for all 8 directions

        for (int i = 0; i < dx.length; i++) {
            int newX = currentX + dx[i];
            int newY = currentY + dy[i];

            // Check if the move is within bounds
            if (newX >= 0 && newX < board.getBoard().length && newY >= 0 && newY < board.getBoard()[0].length) {
                Piece targetPiece = board.getPiece(newX, newY);

                // Add to moves if target cell is empty or contains an opponent's piece
                if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                    moves.add(new int[]{newX, newY});
                }
            }
        }

        return moves;
    }

    @Override
    public void move(int currentX, int currentY, Board board) {
        // The parent class `move` will handle the actual movement using available moves
        super.move(currentX, currentY, board);
    }


}
