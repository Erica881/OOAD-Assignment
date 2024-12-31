package Model;

public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][5]; // 5x8 grid for the board

        // Place tor, biz, sau... using for loop
        for (int i = 0; i <= 7; i += 7){
                board[i][0] = new Tor(i == 0 ? "Red" : "Blue"); // Ram piece at position (0, 0)
                board[i][1] = new Biz(i == 0 ? "Red" : "Blue");
                board[i][2] = new Sau(i == 0 ? "Red" : "Blue");
                board[i][3] = new Biz(i == 0 ? "Red" : "Blue");
                board[i][4] = new Tor(i == 0 ? "Red" : "Blue");
        }
        
        // Place ram repeatly 
        for (int i = 1;i <= 6; i += 5) {
            for (int j = 0; j <= 4; j++){
                board[i][j] = new Ram( i == 1 ? "Red" : "Blue");
            }
        } 
    }
    
    public Piece getPiece(int x, int y) {
        return board[x][y]; // Return the piece at the given position
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece; // Set a piece at the given position
    }
    
    public void moveRam(int currentX, int currentY) {
        Piece piece = board[currentX][currentY];

        if (piece instanceof Ram) {
            Ram ram = (Ram) piece;
            ram.moveForward(currentX, currentY, board);
        }
    }
}