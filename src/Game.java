import Controller.GameController;
import Model.Board;
import View.BoardView;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        // Create the model, view, and controller
        Board board = new Board();
        BoardView view = new BoardView();
        GameController controller = new GameController(board, view);

        // Set up the window
        JFrame frame = new JFrame("Kwazam Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 600); // Adjust the window size to fit the grid
        frame.add(view);
        frame.setVisible(true);
    }
}