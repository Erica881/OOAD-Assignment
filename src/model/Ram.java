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
        System.out.println("board flip in ram: " + board.isFlipped());
        // Generate available moves for the Ram piece
        int newX = board.isFlipped() ? x + 1 : x - 1;
        // for (int i = 0; i <= 2; i++) {

        // newX = x - i;

        // Check if the new position is within the board boundaries
        if (newX >= 0 && newX < board.getBoard().length && y < board.getBoard()[0].length) {
            // Check if the new position is empty or occupied by an opponent's piece
            if (board.getPiece(newX, y) == null
                    || !board.getPiece(newX, y).getColor().equals(this.getColor())) {
                availableMoves.add(new int[] { newX, y });
            }
        }

        // // Print the available moves
        // System.out.println("Available moves for Ram at (" + x + ", " + y + "):");
        // for (int[] move : availableMoves) {
        // System.out.println("(" + move[0] + ", " + move[1] + ")");
        // }

        return availableMoves;
    }
}
