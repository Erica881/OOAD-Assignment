package model;

import java.util.ArrayList;
import java.util.List;

public class Ram extends Piece {
    private int direction = 1;
    private boolean moveBack = false;
    private String colorTurn;
    // private int x, y; // Current position of the piece

    public Ram(String colorTurn, int x, int y) {
        super("Ram", colorTurn, x, y);
        this.direction = 1;
        this.colorTurn = colorTurn;
    }

    @Override
    public String getName() {
        return "Ram";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    public void move(int toX, int toY, Board board) {
        System.out.println("In Ram, x: " + getX() + " y: " + getY() + " toX: " + toX + " toY: " + toY);

        // delete the previous piece when havnt move
        board.setPiece(getX(), getY(), null);
        board.setPiece(toX, toY, this);

        System.out.println("Successfully Moved to: (" + toX + ", " + toY + ")");
    }

    public void movingBack(boolean backward) {
        this.moveBack = backward;
    }

    @Override
    public List<int[]> getAvailableMoves(int x, int y, Board board) {
        List<int[]> availableMoves = new ArrayList<>();

        // Example: Ram can move 1 square forward and backward
        // Example: Ram can move 2 squares forward and backward
        int forward = x - 1;
        int backward = x + 1;

        if (x == 0) {
            moveBack = true;
        }

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

        for (int[] move : availableMoves) {
            System.out.println("Available moves in Ram class (" + move[0] + ", " + move[1] + ")");

        }
        return availableMoves;
    }
}
