package model;

import java.util.ArrayList;
import java.util.List;
import controller.GameController;

public class Ram extends Piece {
    private int direction = 1;
    private boolean moveBack = false;
    private GameController controller;
    // private int x, y; // Current position of the piece

    public Ram(String colorTurn, int x, int y) {
        super("Ram", colorTurn, x, y);
        this.x = x;
        this.y = y;
        this.direction = 1;
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

    // @Override
    // public void move(int currentX, int currentY, Board board) {
    // int newX;

    // if (this.moveBack) {
    // newX = currentX + this.direction;
    // } else {
    // newX = currentX - this.direction;
    // }

    // if (newX == 0) {
    // this.movingBack(true);
    // }

    // if (newX >= 0 && newX < board.getBoard().length) {

    // Piece targetPosition = board.getPiece(newX, currentY);

    // if (targetPosition == null) {

    // board.setPiece(newX, currentY, this);
    // board.setPiece(currentX, currentY, null);

    // } else if (!targetPosition.getColor().equals(this.getColor())) {

    // System.out.println(targetPosition.getNameNColour() + " has been eliminated by
    // " + this.getNameNColour()
    // + " at (" + newX + ", " + currentY + ").");

    // board.setPiece(newX, currentY, this);
    // board.setPiece(currentX, currentY, null);

    // }
    // }
    // }

    public void move(int toX, int toY, Board board) {
        System.out.println("move need to be done in RAM");
        // delete the previous piece when havnt move
        board.setPiece(x, y, null);
        board.setPiece(toX, toY, this);

        this.x = toX;
        this.y = toY;

        System.out.println("Successfully Moved to: (" + toX + ", " + toY + ")");
    }

    @Override
    public List<int[]> getAvailableMoves(int x, int y, Board board) {
        List<int[]> availableMoves = new ArrayList<>();

        // Example: Ram can move 1 square forward and backward
        // Example: Ram can move 2 squares forward and backward
        int forward = x - 1;
        int forward2 = x - 2;
        int backward = x + 1;
        int backward2 = x + 2;

        // Check forward move
        if (forward >= 0 && forward < board.getBoard().length) {
            if (board.getPiece(forward, y) == null) { // No piece in the target cell
                availableMoves.add(new int[] { forward, y });
            }
        }
        if (forward2 >= 0 && forward2 < board.getBoard().length) {
            if (board.getPiece(forward2, y) == null) {
                availableMoves.add(new int[] { forward2, y });
            }
        }

        // Check backward move
        if (backward >= 0 && backward < board.getBoard().length) {
            if (board.getPiece(backward, y) == null) { // No piece in the target cell
                availableMoves.add(new int[] { backward, y });
            }
        }

        // You can add additional logic here for other moves, such as diagonals,
        // captures, etc.

        for (int[] move : availableMoves) {
            System.out.println("Available moves in Ram class (" + move[0] + ", " + move[1] + ")");

        }
        return availableMoves;
    }
}
