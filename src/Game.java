import Controller.GameController;
import Model.Board;
import View.BoardView;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        BoardView view = new BoardView();
        GameController controller = new GameController(board, view);
    }

}
