package Model;

public class Ram extends Piece {
    private int direction;
    
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

    public void moveForward(int currentX, int currentY, Piece[][] board) {
        int targetX;

        if (currentX == 1) {
            targetX = currentX + direction;
        } else {
            targetX = currentX - direction;
        }

        // Check if the target position is within bounds and not occupied
        if (targetX >= 0 && targetX < board.length && board[targetX][currentY] == null) {
            // Move the Ram piece
            board[targetX][currentY] = this;
            board[currentX][currentY] = null;

            // Update direction if Ram reaches the end of the board
            if (targetX == 0) {
                direction = 1; // Start moving forward
            } else if (targetX == board.length - 1) {
                direction = -1; // Start moving backward
            }
        }
    }
}
