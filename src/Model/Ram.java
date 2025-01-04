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

    @Override
    public void move(int currentX, int currentY, Board board) {
        int newX = currentX - direction;

        if (newX >= 0 && newX < board.getBoard().length) {
            Piece targetPosition = board.getPiece(newX, currentY);

            if (targetPosition == null) {

                board.setPiece(newX, currentY, this);
                board.setPiece(currentX, currentY, null);

            } else if (!targetPosition.getColor().equals(this.getColor())) {

                System.out.println(targetPosition.getNameNColour() +" has been eliminated by " + this.getNameNColour() + " at (" + newX + ", " + currentY + ")." );
               
                board.setPiece(newX, currentY, this);
                board.setPiece(currentX, currentY, null);
                
            }
        }

    // @Override
    // public void move(int currentX, int currentY, Board board) {
    //     int newX = currentX + direction; // Assume Ram always moves forward.
    //     if (newX >= 0 && newX < board.length && board[newX][currentY] == null) {
    //         // Move the Ram piece
    //         board[newX][currentY] = this;
    //         board[currentX][currentY] = null;

    //     } else if (board[newX][currentY] != null) {
    //         setKilled(newX, currentY);
    //         System.out.println(Piece.getName(newX, currentY) +" at " + board[newX][currentY] + " has been eliminated by " + this.getName());
            
    //         board.setPiece(newX, currentY, this);
    //         board.setPiece(currentX, currentY, null);
            
    //         return; // Invalid move.
    //     }
    // }
}

    // public void move(int currentX, int currentY, Piece[][] board) {
    //     int targetX;

    //     if (currentX == 1) {
    //         targetX = currentX + direction;
    //     } else {
    //         targetX = currentX - direction;
    //     }

    //     // Check if the target position is within bounds and not occupied
    //     if (targetX >= 0 && targetX < board.length && board[targetX][currentY] == null) {
    //         // Move the Ram piece
    //         board[targetX][currentY] = this;
    //         board[currentX][currentY] = null;

    //         // Update direction if Ram reaches the end of the board
    //         if (targetX == 0) {
    //             direction = 1; // Start moving forward
    //         } else if (targetX == board.length - 1) {
    //             direction = -1; // Start moving backward
    //         }
    //     }
    // }
}

