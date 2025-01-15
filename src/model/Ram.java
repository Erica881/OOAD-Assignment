package model;

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
    public void move(int currentX, int currentY, Board board) {
        int newX;

        if (this.moveBack) {
            newX = currentX + this.direction;
        } else {
            newX = currentX - this.direction;
        }

        if (newX == 0) {
            this.movingBack(true);
        }

        if (newX >= 0 && newX < board.getBoard().length) {

            Piece targetPosition = board.getPiece(newX, currentY);

            if (targetPosition == null) {

                board.setPiece(newX, currentY, this);
                board.setPiece(currentX, currentY, null);

            } else if (!targetPosition.getColor().equals(this.getColor())) {

                System.out.println(targetPosition.getNameNColour() + " has been eliminated by " + this.getNameNColour()
                        + " at (" + newX + ", " + currentY + ").");

                board.setPiece(newX, currentY, this);
                board.setPiece(currentX, currentY, null);

            }
        }
    }
}
