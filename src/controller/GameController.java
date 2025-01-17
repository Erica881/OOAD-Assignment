package controller;

import model.*;
import model.sound.Sound;
import view.MainView;
import utility.*;
import utility.Stopwatch.GameTimerListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements GameTimerListener {
    private Board board; // The model
    private MainView mainView; // The main view
    private Sound sound;
    private final LogManager logManager; // Log manager
    private boolean isMuted = false; // Centralized mute state
    private Stopwatch stopwatch;

    // private BoardView boardView; // The board view within MainView
    private String currentPlayer = "Blue";
    String logMessage;
    String errorMessage;

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
        Piece selectedPiece = board.getPiece(x, y);

        if (isSelectedPieceValidate(selectedPiece)) {
            logMessage = currentPlayer + " selected " + selectedPiece.getName() + " at (" + x +
                    ", " + y + ")";

            sound.soundMove();
            System.out.println(logMessage);
            updateGameState(x, y, logMessage);
        }

    }

    private void updateGameState(int x, int y, String logMessage) {
        mainView.updateStatus(logMessage);
        board.movePiece(x, y);
        board.flipBoard();

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
        stopwatch.start(); // Delegate to model
        // board-related logic
        logManager.initializeSaveFile();
        attachBoardListener();

        // Update the board view with the initial state of the board
        updateBoardView();

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
                        handleCellClick(x, y);
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

    public static void main(String[] args) {
        // Initialize the model and start the game
        Board board = new Board();
        new GameController(board);
    }

    // Starts the timer
    public void startTimer() {
        stopwatch.start();
    }

    // Stops the timer
    public void stopTimer() {
        stopwatch.stop();
    }

    public void pauseGame() {
        if (stopwatch != null) {
            stopwatch.pause(); // Pause the game timer
        }
        mainView.updateStatus("Game paused!");
    }

    // Resets the timer
    public void resetTimer() {
        stopwatch.reset();
    }

    // Method from the GameTimerListener interface to update the view
    @Override
    public void onTimeUpdate(int seconds) {
        // Inform the view to update the time label with the current seconds
        mainView.updateTimeLabel(seconds);
    }

    public void resumeGame() {
        if (stopwatch != null) {
            startTimer(); // Resume the game timer if it's paused
        }
        mainView.updateStatus("Game resumed!"); // Optionally update the status label
    }
}