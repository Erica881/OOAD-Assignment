package model;

import java.util.ArrayList;

public class Ram extends Piece {
    private int direction = 1;
    private boolean moveBack = false;

    public Ram(String colorTurn) {
        super("Ram", colorTurn);
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

    @Override
    public ArrayList<int[]> getAvailableMoves(int currentX, int currentY, Board board) {
        ArrayList<int[]> moves = new ArrayList<>();
        int newX;

        // Move forward or backward depending on the state
        if (moveBack) {
            newX = currentX + direction;
        } else {
            newX = currentX - direction;
        }

        if (newX == 0) {
            this.movingBack(true);
        }

        if (newX >= 0 && newX < board.getBoard().length) {
            Piece targetPiece = board.getPiece(newX, currentY);

            // Add to moves if target cell is empty or contains an opponent's piece
            if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                moves.add(new int[]{newX, currentY});
            }
        }

        return moves;
    }

    @Override
    public void move(int currentX, int currentY, Board board) {
        Piece selectedPiece = board.getPiece(currentX, currentY);
        ArrayList<int[]> availableMoves = getAvailableMoves(currentX, currentY, board);

        // Loop through available moves to execute one (this logic should already ensure valid moves are passed)
        if (!availableMoves.isEmpty()) {
            int[] move = availableMoves.get(0); // Always pick the first move
            int targetX = move[0];
            int targetY = move[1];

            // Perform the move: Update the board
            board.setPiece(targetX, targetY, this);
            board.setPiece(currentX, currentY, null);

            selectedPiece.setFormatCoordinate(targetX, targetY, board.isFlipped());

            System.out.println(this.getNameNColour() + " moved to" + selectedPiece.getCoordinate());

            return; // Exit after executing the move
        }
        
        // If no valid moves, log an error
        System.err.println("No valid moves available for " + this.getNameNColour());
    }
    

    //System.err.println("No valid moves available for " + this.getNameNColour());
}

