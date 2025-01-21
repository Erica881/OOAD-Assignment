package model;

import java.util.ArrayList;
import java.util.List;
import controller.GameController;

public class Ram extends Piece {
    private int direction = 1;
    private boolean moveBack = false;

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
        board.setPiece(toX, toY, this);

        this.x = toX;
        this.y = toY;

        System.out.println("Successfully Moved to: (" + toX + ", " + toY + ")");
    }

    @Override
    public List<int[]> getAvailableMoves(int x, int y, Board board) {
        List<int[]> availableMoves = new ArrayList<>();
        // int newX;

        // Example: Ram can move 1 square forward and backward
        // Example: Ram can move 2 squares forward and backward
        int forward = x - 1;
        int backward = x + 1;

        System.out.println("x in getAvailableMove in Ram class (" + x + ", " + y + ")");

        if (x == 0) {
            moveBack = true;
        }

        System.out.println("moveBack: " + moveBack);

        // Check forward move
        if (moveBack == false && forward >= 0 && forward < board.getBoard().length) {
            if (board.getPiece(forward, y) == null) { // No piece in the target cell
                availableMoves.add(new int[] { forward, y });
            } else {
                System.out.println("Have enemy at " + forward + ", " + y);
                // should be call availableEnemyMoves (change button to red)
                availableMoves.add(new int[] { forward, y });
            }
        } else if (moveBack == true) {
            // moveBack = true;
            availableMoves.add(new int[] { backward, y });
        }

        // // Check backward move
        // if (backward >= 0 && backward < board.getBoard().length) {
        // if (board.getPiece(backward, y) == null) { // No piece in the target cell
        // availableMoves.add(new int[] { backward, y });
        // }
        // }

        // You can add additional logic here for other moves, such as diagonals,
        // captures, etc.

        for (int[] move : availableMoves) {
            System.out.println("Available moves in Ram class (" + move[0] + ", " + move[1] + ")");

        }
        return availableMoves;
    }
}
