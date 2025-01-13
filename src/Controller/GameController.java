package Controller;

import Model.Board;
import Model.Piece;
import Model.Ram;
import View.BoardView;
import View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameController {
    private Board board; // The model
    private MainView mainView; // The main view
    // private BoardView boardView; // The board view within MainView
    private String currentPlayer = "Blue";

    private static final String SAVE_FILE_PATH = System.getProperty("user.dir")
            + "/src/resources/savedGames/game_log.txt";

    public GameController(Board board) {
        this.board = board;

        // Initialize views
        mainView = new MainView(this);

        mainView.display();

    }

    private void handleCellClick(int x, int y) {
        Piece piece = board.getPiece(x, y);
        String logMessage;

        if (piece == null) {
            logMessage = "Empty cell clicked at (" + x + ", " + y + ")";
            mainView.updateStatus(logMessage); // Update status label
        } else if (piece.getColor().equalsIgnoreCase(currentPlayer)) {
            logMessage = currentPlayer + " selected " + piece.getName() + " at (" + x +
                    ", " + y + ")";
            System.out.println(logMessage);
            mainView.updateStatus(logMessage);

            if (piece instanceof Ram) {
                board.moveRam(x, y);

                // Flip the board after the move
                board.flipBoard();

                // Toggle the current player
                currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";

                // Update the board view
                updateBoardView();

                mainView.updateStatus("Board flipped. It's now " + currentPlayer + "'s turn.");
            }
        } else {
            logMessage = "It's not " + piece.getColor() + "'s turn.";
            mainView.updateStatus(logMessage);
        }

        saveLog(logMessage);
    }

    private void initializeSaveFile() {
        try {
            File saveFile = new File(SAVE_FILE_PATH);

            // Ensure the parent directory exists
            File parentDir = saveFile.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            // Clear the content of the save file on startup
            if (!saveFile.exists()) {
                if (!saveFile.createNewFile()) {
                    throw new IOException("Failed to create file: " + saveFile.getAbsolutePath());
                }
            } else {
                new FileWriter(saveFile, false).close(); // Clear file content
            }
        } catch (IOException e) {
            System.err.println("Error initializing save file: " + e.getMessage());
        }
    }

    private void saveLog(String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to save file: " + e.getMessage());
        }
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

        // board-related logic
        initializeSaveFile();

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

        // Show the main view
        mainView.getBoardView();

        // Update the board view with the initial state of the board
        updateBoardView();

    }

    public static void main(String[] args) {
        // Initialize the model and start the game
        Board board = new Board();
        new GameController(board);
    }
}
