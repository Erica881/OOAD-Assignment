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
    public boolean move(int currentX, int currentY, int targetX, int targetY, Board board) {
        // Calculate the target position
        int newX = currentX + 1;

        // Ensure the move is within bounds and the target cell is either empty or occupied by an opponent's piece
        if (newX < board.getBoard().length) {
            Piece targetPiece = board.getPiece(newX, currentY);
            if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                // If the target cell is occupied, mark the opponent's piece as "killed"
                if (targetPiece != null) {
                    targetPiece.setKilled(true);
                    System.out.println(targetPiece.getName() + " has been eliminated by " + this.getName());
                }

                // Perform the move
                board.setPiece(newX, currentY, this);
                board.setPiece(currentX, currentY, null);
                return true;
            }
        }   
            return false; // Move was invalid
        }
}

