import model.Board;

import controller.GameController;

public class GameLauncher {
    public static void main(String[] args) {
        // Initialize the model and start the game
        Board board = new Board();
        new GameController(board);
    }
}
