// package controller;

// import model.*;
// import model.sound.Sound;
// import view.BoardView;
// import view.MainView;
// import view.MenuView;
// import utility.*;
// import utility.Stopwatch.GameTimerListener;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// import javax.swing.JButton;

// public class GameController implements GameTimerListener {
//     private Board board; // The model
//     private MainView mainView; // The main view
//     private Sound sound;
//     private final LogManager logManager; // Log manager
//     private boolean isMuted = false; // Centralized mute state
//     private Stopwatch stopwatch;

//     private int selectedPieceX = -1; // Store the X coordinate of the selected piece
//     private int selectedPieceY = -1; // Store the Y coordinate of the selected piece
//     private List<int[]> availableMoves;
//     private Piece selectedPiece;

//     // private BoardView boardView; // The board view within MainView
//     private String currentPlayer = "Blue";
//     String logMessage;
//     String errorMessage;

//     public GameController(Board board) {
//         this.board = board;

//         // initialize log manager
//         this.logManager = new LogManager();

//         this.stopwatch = new Stopwatch(this);

//         // Initialize views
//         mainView = new MainView(this);
//         sound = new Sound(this);
//         mainView.display();

//     }

//     public void handleCellClick(int x, int y) {
//         sound = new Sound(this);
//         selectedPiece = board.getPiece(x, y);

//         if (isSelectedPieceValidate(selectedPiece)) {
//             selectedPieceX = x;
//             selectedPieceY = y;
//             // Get available moves for the selected piece (polymorphic call)
//             availableMoves = selectedPiece.getAvailableMoves(x, y, board);

//             mainView.getBoardView().showAvailableMoves(availableMoves);

//         }
//     }

//     public void movePiece(int x, int y, Piece selectedPiece) {
//         selectedPiece = this.selectedPiece;
//         if (selectedPiece == null) {
//             System.out.println("No piece selected for moving.");
//             return;
//         }

//         // Update the board model
//         board.setPiece(x, y, selectedPiece);
//         board.setPiece(selectedPieceX, selectedPieceY, null); // Clear the old position

//         System.out.println(selectedPiece.getName() + " piece moved from (" + selectedPieceX + ", " + selectedPieceY
//                 + ") to (" + x + ", " + y + ")");
//         logMessage = currentPlayer + " moved " + selectedPiece.getName() + " from (" + selectedPieceX + ", "
//                 + selectedPieceY + ") to (" + x + ", " + y + ")";

//         // Update the view
//         updateBoardView();
//         mainView.updateStatus(logMessage);

//         // Switch players (if applicable)
//         currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
//         mainView.updateStatus("It's now " + currentPlayer + "'s turn.");

//         // Reset the selected piece
//         selectedPiece = null;
//         selectedPieceX = -1;
//         selectedPieceY = -1;
//     }

//     public void updateGameState(int x, int y, String logMessage) {
//         mainView.updateStatus(logMessage);
//         // board.movePiece(x, y);
//         board.flipBoard();

//         // Switch players
//         currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";

//         updateBoardView();
//         mainView.updateStatus("Board flipped. It's now " + currentPlayer + "'s turn.");
//         logManager.logAction(logMessage);
//     }

//     public boolean isSelectedPieceValidate(Piece selectedPiece) {
//         if (selectedPiece == null || (!selectedPiece.getColor().equalsIgnoreCase(currentPlayer))) {
//             sound.soundNotify();
//             errorMessage = "Please select any " + currentPlayer + " piece to move.";
//             mainView.updateStatus(errorMessage); // Update status label
//             return false;
//         }
//         return true;
//     }

//     private void updateBoardView() {
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 5; j++) {
//                 Piece piece = board.getPiece(i, j);
//                 mainView.getBoardView().updateCell(i, j, piece);
//             }
//         }
//     }

//     public void startGame() {
//         stopwatch.start();
//         // board-related logic
//         logManager.initializeSaveFile();
//         board.initialize(); // Call initialize() to set up the board board.
//         attachBoardListener();

//         // Update the board view with the initial state of the board
//         updateBoardView();

//     }

//     public void attachBoardListener() {
//         // Attach listeners to the board cells
//         for (int i = 0; i < 8; i++) {
//             for (int j = 0; j < 5; j++) {
//                 int x = i;
//                 int y = j;

//                 mainView.getBoardView().addCellListener(x, y, new ActionListener() {

//                     @Override
//                     public void actionPerformed(ActionEvent e) {
//                         System.out.println("i am in atatch board listener");
//                         handleCellClick(x, y);

//                     }
//                 });
//             }
//         }
//     }

//     public void setMute(boolean mute) {
//         isMuted = mute;
//         String status = isMuted ? "Sound muted." : "Sound unmuted.";
//         mainView.updateStatus(status);
//         mainView.updateSoundIcon(mute);
//     }

//     public boolean getMuteStatus() {
//         return isMuted;
//     }

//     // Stops the timer
//     public void stopTimer() {
//         stopwatch.stop();
//     }

//     // Method from the GameTimerListener interface to update the view
//     @Override
//     public void onTimeUpdate(int seconds) {
//         // Inform the view to update the time label with the current seconds
//         mainView.updateTimeLabel(seconds);
//     }

//     public void resumeGame() {
//         if (stopwatch != null) {
//             stopwatch.start();// Resume the game timer if it's paused
//         }
//         mainView.updateStatus("Game resumed!"); // Optionally update the status label
//     }

//     public void resetGame() {
//         stopwatch.reset();
//         stopwatch.start();
//         currentPlayer = "Blue"; // Reset the player to the initial player
//         board.initialize(); // Reinitialize the board
//         updateBoardView(); // Update the board view to reflect the new state
//         logManager.initializeSaveFile(); // Optionally reset logs
//         mainView.updateStatus("Board has been reset."); // Update the status
//         System.out.println("Log reseted.");
//     }

//     public void stopGame() {
//         stopwatch.reset();
//         board.initialize();
//         mainView.getFrame().setVisible(false); // Hides the current game view
//         GameController newGameController = new GameController(board);
//         newGameController.mainView.getFrame().setVisible(true); // Show the new game view

//         // new GameController(board);
//         mainView.updateStatus("Game stopped!");
//     }

// }

package controller;

import model.*;
import model.sound.Sound;
import view.MainView;
import view.BoardView;
import utility.LogManager;
import utility.Stopwatch;
import utility.Stopwatch.GameTimerListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameController implements GameTimerListener {
    private Board board;
    private MainView mainView;
    private Sound sound;
    private LogManager logManager;
    private Stopwatch stopwatch;
    private boolean isMuted;
    private Piece selectedPiece;
    private List<int[]> availableMoves;
    private String currentPlayer = "Blue";

    public GameController(Board board) {
        this.board = board;
        this.logManager = new LogManager();
        this.sound = new Sound(this);
        this.stopwatch = new Stopwatch(this);
        this.mainView = new MainView(this);
        mainView.display();
    }

    public void startGame() {
        stopwatch.start();
        board.initialize();
        logManager.initializeSaveFile();
        updateBoardView();
        attachBoardListener();
    }

    private void attachBoardListener() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                int x = i;
                int y = j;
                mainView.getBoardView().addCellListener(x, y, e -> handleCellClick(x, y));
            }
        }
    }

    public void handleCellClick(int x, int y) {
        selectedPiece = board.getPiece(x, y);
        if (isSelectedPieceValid(selectedPiece)) {
            availableMoves = selectedPiece.getAvailableMoves(x, y, board);
            mainView.getBoardView().showAvailableMoves(availableMoves);
        }
    }

    public void movePiece(int x, int y) {
        if (isMoveValid(x, y, availableMoves)) {
            board.movePiece(x, y, selectedPiece);
            logMove(x, y);
            switchPlayer();
            updateBoardView();
        }
    }

    private void logMove(int x, int y) {
        String logMessage = currentPlayer + " moved " + selectedPiece.getName() + " to (" + x + ", " + y + ")";
        mainView.updateStatus(logMessage);
        board.movePiece(x, y, selectedPiece);
        board.flipBoard();

        // Rotate images for all pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece != null) {
                    piece.rotateImage(); // Rotate the image of the piece
                }
            }
        }

        // Switch players
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";

        updateBoardView();
        mainView.updateStatus("Board flipped. It's now " + currentPlayer + "'s turn.");
        logManager.logAction(logMessage);
    }

    private boolean isMoveValid(int x, int y, List<int[]> availableMoves) {
        for (int[] move : availableMoves) {
            if (move[0] == x && move[1] == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isSelectedPieceValid(Piece piece) {
        if (piece == null || !piece.getColor().equalsIgnoreCase(currentPlayer)) {
            sound.soundNotify();
            mainView.updateStatus("Select a valid " + currentPlayer + " piece.");
            return false;
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
    }

    private void updateBoardView() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                mainView.getBoardView().updateCell(i, j, board.getPiece(i, j));
            }
        }
    }

    public void setMute(boolean mute) {
        isMuted = mute;
        mainView.updateStatus(isMuted ? "Sound muted." : "Sound unmuted.");
        mainView.updateSoundIcon(mute);
    }

    public boolean getMuteStatus() {
        return isMuted;
    }

    @Override
    public void onTimeUpdate(int seconds) {
        mainView.updateTimeLabel(seconds);
    }

    public void stopTimer() {
        stopwatch.stop();
    }

    public void resumeGame() {
        stopwatch.start();
        mainView.updateStatus("Game resumed!");
    }

    public void resetGame() {
        stopwatch.reset();
        board.initialize();
        mainView.getBoardView().clearHighlights();
        updateBoardView();
        logManager.initializeSaveFile();
        mainView.updateStatus("Game reset.");
    }

    public void stopGame() {
        stopwatch.reset();
        board.initialize();
        mainView.getFrame().setVisible(false);
        new GameController(board).mainView.getFrame().setVisible(true);
        mainView.updateStatus("Game stopped!");
    }
}
