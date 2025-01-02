package Model;

public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][5]; // 5x8 grid for the board

        // Place pieces for demonstration
        /*for (int i = 0; i <= 6; i+= 6){
            for (int j = 0; j <= 4; j++) {
                board[i][j] = new Tor(); // Ram piece at position (0, 0)
                board[i][j] = new Biz();
                board[0][j] = new Sau();
                board[0][j] = new Biz();
                board[0][4] = new Tor(); 
            }
        } */
        
        board[0][0] = new Tor(); // Ram piece at position (0, 0)
        board[0][1] = new Biz();
        board[0][2] = new Sau();
        board[0][3] = new Biz();
        board[0][4] = new Tor(); 

        board[7][4] = new Tor(); // Ram piece at position (4, 7)
        board[7][3] = new Biz();
        board[7][2] = new Sau();
        board[7][1] = new Biz();
        board[7][0] = new Tor();  

        for (int i = 1;i <= 6; i += 5) {
            for (int j = 0; j <= 4; j++){
                board[i][j] = new Ram();
            }
        }
    } 
    
    

    public Piece getPiece(int x, int y) {
        return board[x][y]; // Return the piece at the given position
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece; // Set a piece at the given position
    }
}
