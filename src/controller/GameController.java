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
            // switchPlayer();
            updateBoardView();
        }
    }

    private void logMove(int x, int y) {
        String logMessage = currentPlayer + " moved " + selectedPiece.getName() + " to (" + x + ", " + y + ")";
        mainView.updateStatus(logMessage);
        board.movePiece(x, y, selectedPiece);
        // Switch players
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
        System.out.println("Current player: " + currentPlayer);
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
