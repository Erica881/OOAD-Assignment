package controller;

import model.*;
import model.sound.Sound;
import view.BoardView;
import view.MainView;
import view.MenuView;
import utility.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameController {
    private Board board; // The model
    private MainView mainView; // The main view
    private Sound sound;
    private final LogManager logManager; // Log manager
    private boolean isMuted = false; // Centralized mute state
    // private Stopwatch stopwatch;
    private ArrayList<int[]> availableMoves = new ArrayList<>();
    // private BoardView boardView; // The board view within MainView
    private String currentPlayer = "Blue";
    private String logMessage;
    private String errorMessage;
    private Piece selectedPiece = null;
    private Piece movePlaceForPiece;
    private boolean isMoveInProgress = false;
    private int turnCounter = 0;
    // private boolean isGameOver = false;
    private TimerController timerController;

    public GameController(Board board) {
        this.board = board;

        // initialize log manager
        this.logManager = new LogManager();
        // Initialize views
        this.mainView = new MainView(this);
        this.timerController = new TimerController(mainView);

        sound = new Sound(this);
        mainView.display();

    }

    private void handleCellClick(int x, int y) {

        // sound = new Sound(this);
        selectedPiece = board.getPiece(x, y);

        // Get the available moves for the selected piece
        availableMoves = selectedPiece.getAvailableMoves(x, y, board);
        ArrayList<int[]> moveContainEnemy = new ArrayList<>();
        for (int[] move : availableMoves) {
            int targetX = move[0];
            int targetY = move[1];
            Piece targetPiece = board.getPiece(targetX, targetY);
            // Check if the move contains an enemy piece
            if (targetPiece != null && !targetPiece.getColor().equals(currentPlayer)) {
                moveContainEnemy.add(move);
            }

        }

        // Highlight available moves and enemy-containing moves
        mainView.getBoardView().highlightAvailableMoves(availableMoves, moveContainEnemy);
        sound.soundMove();
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    private void updateGameState(int x, int y, String logMessage) {
        mainView.updateStatus(logMessage);
        mainView.getBoardView().clearHighlights();

        // Trigger Tor/Xor transformation every 2 turns
        if (turnCounter >= 2) {
            torTransformation();
        }

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
        // stopwatch.start();
        timerController.startTimer();
        // board-related logic
        logManager.initializeSaveFile();
        board.initialize(); // Call initialize() to set up the board board.
        attachBoardListener();
        // Update the board view with the initial state of the board
        updateBoardView();

    }

    public String formatCoordinate(int x, int y) {
        char columnLetter = (char) ('a' + y); // Adjust
        // column for flipped board
        int rowNumber = x + 1; // Adjust row number for flipped board
        return "(" + rowNumber + ", " + columnLetter + ")";
    }

    public void logMove(int boardX, int boardY) {
        movePlaceForPiece = board.getPiece(boardX, boardY);
        for (int[] move : availableMoves) {
            if (move[0] == boardX && move[1] == boardY) {
                logMessage = selectedPiece.getColor() + " " + selectedPiece.getName() + " move from "
                        + formatCoordinate(selectedPiece.getX(), selectedPiece.getY())
                        + " to " + formatCoordinate(boardX, boardY);
                board.movePiece(boardX, boardY, selectedPiece);
                if (board.isPieceCapture()) {
                    logMessage += " and captured " + board.getCapturePiece().getColor() + " "
                            + board.getCapturePiece().getName() + " at "
                            + formatCoordinate(board.getCapturePiece().getX(), board.getCapturePiece().getY());
                }
                movePlaceForPiece = selectedPiece;
                sound.soundMove();
                turnCounter++;
                updateGameState(boardX, boardY, logMessage);
                isMoveInProgress = false; // Mark the move as completed
                return; // Exit to prevent further execution
            }
        }
    }

    public void torTransformation() {
        // Perform transformation every 2 turns
        if (turnCounter % 2 == 0) {
            board.transformTorXor();

            // Update the selected piece reference if it was transformed
            if (selectedPiece != null) {
                int x = selectedPiece.getX();
                int y = selectedPiece.getY();
                selectedPiece = board.getPiece(x, y);
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

                        // Check if a valid piece is selected for the current player
                        Piece pieceAtCell = board.getPiece(boardX, boardY);

                        if (isSelectedPieceValidate(pieceAtCell) && !isMoveInProgress) {
                            // Handle piece selection
                            handleCellClick(boardX, boardY);

                        } else {
                            isMoveInProgress = true;
                            logMove(boardX, boardY);
                            checkGameEnd();
                        }
                        isMoveInProgress = false;
                        return;
                    }
                });
            }

        }
        return;

    }

    private void checkGameEnd() {

        boolean isBlueSauAlive = false;
        boolean isRedSauAlive = false;

        // Iterate through the board to check if both Sau pieces exist
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                // Piece piece = board.getPiece(i, j);
                Piece piece = board.getPiece(i, j);
                if (piece instanceof Sau) {
                    if (piece.getColor().equalsIgnoreCase("blue")) {
                        isBlueSauAlive = true;
                    } else if (piece.getColor().equalsIgnoreCase("red")) {
                        isRedSauAlive = true;
                    }
                }
            }
        }
        // Determine game-ending conditions
        if (!isBlueSauAlive) {
            endGame("Red"); // Red wins
        } else if (!isRedSauAlive) {
            endGame("Blue"); // Blue wins
        }

    }

    private void endGame(String winingPlayer) {
        // Disable further moves or reset the game
        mainView.showWinningView(winingPlayer);
        // isGameOver = true; // Add a flag to track the game's state
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

    public void resumeGame() {
        timerController.startTimer();
        mainView.updateStatus("Game resumed!"); // Optionally update the status label
    }

    public void pauseGame() {
        timerController.stopTimer();
    }

    public void resetGame() {
        timerController.resetTimer();
        currentPlayer = "Blue"; // Reset the player to the initial player
        board.initialize(); // Reinitialize the board
        updateBoardView(); // Update the board view to reflect the new state
        logManager.initializeSaveFile(); // Optionally reset logs
        mainView.updateStatus("Board has been reset."); // Update the status
        System.out.println("Log reseted.");
    }

    public void stopGame() {
        mainView.getFrame().setVisible(false); // Hides the current game view

        timerController.stopTimer();
        board.initialize();
        currentPlayer = "Blue"; // Reset the player to the initial player
        mainView.getBoardView().initialBoard();
        GameController newGameController = new GameController(board);
        newGameController.mainView.getFrame().setVisible(true); // Show the new game
        logManager.initializeSaveFile(); // Optionally reset logs
        mainView.updateStatus("Game stopped!");
    }

}