// // import Controller.GameController;
// // import Model.Board;
// // import View.BoardView;

// // import javax.swing.*;

// // public class Game {
// //     public static void main(String[] args) {
// //         // Create the model, view, and controller
// //         Board board = new Board();
// //         BoardView view = new BoardView();
// //         GameController controller = new GameController(board, view);

// //         // Set up the window
// //         JFrame frame = new JFrame("Kwazam Chess");
// //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// //         frame.setSize(480, 400); // Adjust the window size to fit the grid
// //         frame.add(view);
// //         frame.setVisible(true);
// //     }
// // }

// import Controller.GameController;
// import Model.Board;
// import View.BoardView;

// import javax.swing.*;
// import java.awt.event.ComponentAdapter;
// import java.awt.event.ComponentEvent;

// public class Game {
//     public static void main(String[] args) {
//         // Create the model, view, and controller
//         Board board = new Board();
//         BoardView view = new BoardView();
//         GameController controller = new GameController(board, view);

//         // Set up the window
//         JFrame frame = new JFrame("Kwazam Chess");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(480, 400); // Adjust the window size to fit the grid
//         frame.add(view);
//         frame.setVisible(true);

//         // Add a resize listener to handle window resizing
//         frame.addComponentListener(new ComponentAdapter() {
//             @Override
//             public void componentResized(ComponentEvent e) {
//                 // Revalidate and repaint the view to ensure proper resizing of components
//                 view.revalidate();
//                 view.repaint();
//             }
//         });
//     }
// }

import javax.swing.JFrame;

import Controller.GameController;
import Model.Board;
import View.BoardView;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        BoardView view = new BoardView();
        GameController controller = new GameController(board, view);

        JFrame frame = new JFrame("Kwazam Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 400);
        frame.add(view);
        frame.setVisible(true);

        // Example of flipping the board during gameplay
        controller.switchTurn(); // Player 2's turn (board flips)
    }
}
