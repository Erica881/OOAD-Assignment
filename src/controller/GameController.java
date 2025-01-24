package controller;

import model.*;
import model.sound.Sound;
import view.BoardView;
import view.MainView;
import view.MenuView;
import utility.*;
import utility.Stopwatch.GameTimerListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class GameController implements GameTimerListener {
    private Board board; // The model
    private MainView mainView; // The main view
    private Sound sound;
    private final LogManager logManager; // Log manager
    private boolean isMuted = false; // Centralized mute state
    private Stopwatch stopwatch;
    private ArrayList<int[]> availableMoves;
    // private BoardView boardView; // The board view within MainView
    private String currentPlayer = "Blue";
    private String logMessage;
    private String errorMessage;
    private Piece selectedPiece;
    private Piece movePlaceForPiece;
    private boolean isPieceSelected = false;
    private boolean isMoveInProgress = false;
    private int turnNumber;

    public GameController(Board board) {
        this.board = board;

        // initialize log manager
        this.logManager = new LogManager();

        this.stopwatch = new Stopwatch(this);

        // Initialize views
        mainView = new MainView(this);
        sound = new Sound(this);
        mainView.display();

    }

    private void handleCellClick(int x, int y) {
        sound = new Sound(this);
        selectedPiece = board.getPiece(x, y);

        if (!isSelectedPieceValidate(selectedPiece)) {
            System.out.println("Invalid piece selection at (" + x + ", " + y + ").");
            return;
        }

        // Get the available moves for the selected piece
        availableMoves = selectedPiece.getAvailableMoves(x, y, board);
        ArrayList<int[]> moveContainEnemy = new ArrayList<>();
        System.out.println("Available moves for " + selectedPiece.getName() + " at (" + x + ", " + y + "):");

        for (int[] move : availableMoves) {
            int targetX = move[0];
            int targetY = move[1];
            Piece targetPiece = board.getPiece(targetX, targetY);

            // Print available move
            System.out.println("Available move: (" + targetX + ", " + targetY + ")");

            // Check if the move contains an enemy piece
            if (targetPiece != null && !targetPiece.getColor().equals(currentPlayer)) {
                moveContainEnemy.add(move);
                System.out.println("Enemy piece found at (" + targetX + ", " + targetY + ").");
            }

        }

        // Highlight available moves and enemy-containing moves
        mainView.getBoardView().highlightAvailableMoves(availableMoves, moveContainEnemy);

        // Log the action
        logMessage = currentPlayer + " selected " + selectedPiece.getName() + " at (" + x + ", " + y + ")";
        sound.soundMove();
        System.out.println(logMessage);
    }

    private void updateGameState(int x, int y, String logMessage) {
        mainView.updateStatus(logMessage);
        mainView.getBoardView().clearHighlights();
        // board.movePiece(x, y);
        board.flipBoard();
        mainView.getBoardView().flipBoardView();

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

    public boolean isSelectedPieceValidate(Piece selectedPiece) {
        if (selectedPiece == null || (!selectedPiece.getColor().equalsIgnoreCase(currentPlayer))) {
            sound.soundNotify();
            errorMessage = "Please select any " + currentPlayer + " piece to move.";
            mainView.updateStatus(errorMessage); // Update status label
            return false;
        }
        return true;
    }

    private void updateBoardView() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Piece piece = board.getPiece(i, j);
                mainView.getBoardView().updateCell(i, j, piece);

            }
        }
    }

    public void startGame() {
        stopwatch.start();
        // board-related logic
        logManager.initializeSaveFile();
        board.initialize(); // Call initialize() to set up the board board.
        attachBoardListener();

        // Update the board view with the initial state of the board
        updateBoardView();

    }

    public void logMove(int boardX, int boardY) {
        movePlaceForPiece = board.getPiece(boardX, boardY);
        for (int[] move : availableMoves) {
            // Format the available move coordinates
            String formattedMove = selectedPiece.formatCoordinate(move[0], move[1],
                    board.isFlipped());
            System.out.println(formattedMove);
            if (move[0] == boardX && move[1] == boardY) {
                board.movePiece(boardX, boardY, selectedPiece);

                movePlaceForPiece = selectedPiece;
                selectedPiece = null;
                System.out.println("Moved piece to (" + movePlaceForPiece.getX() + ", "
                        + movePlaceForPiece.getY() + ")");
                updateGameState(boardX, boardY, logMessage);

                isMoveInProgress = false; // Mark the move as completed
                return; // Exit to prevent further execution
            }
        }
    }

    public void attachBoardListener() {
        // Attach listeners to the board cells
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                final int x = i;
                final int y = j;

                mainView.getBoardView().addCellListener(x, y, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Map view coordinates to the correct board coordinates
                        int[] mappedCoords = board.mapViewToBoardCoordinates(x, y);
                        int boardX = mappedCoords[0];
                        int boardY = mappedCoords[1];

                        if (board.getPiece(boardX, boardY) != null
                                && board.getPiece(boardX, boardY).getColor().equalsIgnoreCase(currentPlayer)) {
                            // Handle selection
                            System.out.println("is move in progress: " + isMoveInProgress);
                            handleCellClick(boardX, boardY);
                        } else {
                            logMove(boardX, boardY);
                        }
                        isMoveInProgress = false; // Reset the flag if no valid move
                    }
                });

            }
        }
    }

    public void setMute(boolean mute) {
        isMuted = mute;
        String status = isMuted ? "Sound muted." : "Sound unmuted.";
        mainView.updateStatus(status);
        mainView.updateSoundIcon(mute);
    }

    public boolean getMuteStatus() {
        return isMuted;
    }

    // Stops the timer
    public void stopTimer() {
        stopwatch.stop();
    }

    // Method from the GameTimerListener interface to update the view
    @Override
    public void onTimeUpdate(int seconds) {
        // Inform the view to update the time label with the current seconds
        mainView.updateTimeLabel(seconds);
    }

    public void resumeGame() {
        if (stopwatch != null) {
            stopwatch.start();// Resume the game timer if it's paused
        }
        mainView.updateStatus("Game resumed!"); // Optionally update the status label
    }

    public void resetGame() {
        stopwatch.reset();
        stopwatch.start();
        currentPlayer = "Blue"; // Reset the player to the initial player
        board.initialize(); // Reinitialize the board
        updateBoardView(); // Update the board view to reflect the new state
        logManager.initializeSaveFile(); // Optionally reset logs
        mainView.updateStatus("Board has been reset."); // Update the status
        System.out.println("Log reseted.");
    }

    public void stopGame() {
        stopwatch.reset();
        board.initialize();
        mainView.getFrame().setVisible(false); // Hides the current game view
        GameController newGameController = new GameController(board);
        newGameController.mainView.getFrame().setVisible(true); // Show the new game view

        // new GameController(board);
        mainView.updateStatus("Game stopped!");
    }

}