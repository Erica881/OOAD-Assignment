// package Model;

// // Abstract class for chess pieces.
// abstract class Piece {
//     protected String name;
//     protected String color;

//     public Piece(String color) {
//         this.color = color;
//     }

//     // Template method for moving.
//     public final boolean move(int startX, int startY, int endX, int endY, Board board) {
//         if (!isMoveValid(startX, startY, endX, endY, board)) {
//             System.out.println("Invalid move for " + name);
//             return false;
//         }
//         performMove(startX, startY, endX, endY, board);
//         return true;
//     }

//     // Abstract method to be implemented by specific pieces.
//     protected abstract boolean isMoveValid(int startX, int startY, int endX, int endY, Board board);

//     // Concrete method for performing the move.
//     private void performMove(int startX, int startY, int endX, int endY, Board board) {
//         board.setPiece(endX, endY, this);
//         board.setPiece(startX, startY, null);
//     }
// }

package Model;

abstract class Piece {
    protected String name;
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    // Template method for moving.
    public final boolean move(int startX, int startY, int endX, int endY, Board board) {
        if (!isMoveValid(startX, startY, endX, endY, board)) {
            System.out.println("Invalid move for " + name);
            return false;
        }
        performMove(startX, startY, endX, endY, board);
        return true;
    }

    protected abstract boolean isMoveValid(int startX, int startY, int endX, int endY, Board board);

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    // public or private
    public void performMove(int startX, int startY, int endX, int endY, Board board) {
        board.setPiece(endX, endY, this);
        board.setPiece(startX, startY, null);
    }
}
