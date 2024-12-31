package Model;

class Ram extends Piece {
    private boolean movingForward = true;

    public Ram(String color) {
        super(color);
        this.name = "Ram";
    }

    @Override
    protected boolean isMoveValid(int startX, int startY, int endX, int endY, Board board) {
        // Ram moves 1 step forward and turns around at the end of the board.
        int direction = movingForward ? -1 : 1;
        if (endX == startX + direction && startY == endY) {
            if (endX < 0 || endX >= 8) {
                movingForward = !movingForward;
            }
            return true;
        }
        return false;
    }
}
