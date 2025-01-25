package model;

import java.util.ArrayList;

public class Ram extends Piece {
    // private int direction = 1;
    private boolean moveBack = false;

    public Ram(String colorTurn) {
        super("Ram", colorTurn);
        // this.direction = 1;
    }

    public Ram(String colorTurn, int x, int y) {
        super("Ram", colorTurn, x, y);
        // this.direction = 1;
    }

    @Override
    public String getName() {
        return "Ram";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    public void movingBack(boolean backward) {
        this.moveBack = backward;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {

        ArrayList<int[]> availableMoves = new ArrayList<>();
        // Generate available moves for the Ram piece
        int newX = board.isFlipped() ? x + 1 : x - 1;

        // Check if the new position is within the board boundaries
        if (newX >= 0 && newX < board.getBoard().length && y < board.getBoard()[0].length && moveBack == false) {
            // Check if the new position is empty or occupied by an opponent's piece
            if (board.getPiece(newX, y) == null
                    || !board.getPiece(newX, y).getColor().equals(this.getColor())) {
                availableMoves.add(new int[] { newX, y });
            }
        } else {
            movingBack(true);
            int moveBackX = board.isFlipped() ? x - 1 : x + 1;

            if (board.getPiece(moveBackX, y) == null
                    || !board.getPiece(moveBackX, y).getColor().equals(this.getColor())) {
                availableMoves.add(new int[] { moveBackX, y });
            }

        }

        return availableMoves;
    }
}
