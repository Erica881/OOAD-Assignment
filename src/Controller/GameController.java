package Controller;

import Model.Board;
import Model.Piece;
import Model.Ram;
import View.BoardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameController {
    private Board board;
    private BoardView view;
    private static final String SAVE_FILE_PATH = "src/resources/savedGames/game_log.txt";

    public GameController(Board board, BoardView view) {
        this.board = board;
        this.view = view;
        initializeSaveFile();
        // Corrected iteration limits for 8 rows and 5 columns
        for (int i = 0; i < 8; i++) { // Loop over 8 rows
            for (int j = 0; j < 5; j++) { // Loop over 5 columns
                final int x = i;
                final int y = j;

                view.addCellListener(x, y, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(x, y);
                    }
                });
            }
        }

        // Update the board view with the current state of the board.
        updateBoardView();
    }

    private void handleCellClick(int x, int y) {
        Piece piece = board.getPiece(x, y);
        String logMessage;

        if (piece != null) {
            logMessage = piece.getColor() + " " + piece.getName() + " selected at (" + x + ", " + y + ")";
            System.out.println(logMessage);

            if (piece instanceof Ram) {
                board.moveRam(x, y);
                updateBoardView(); // Refresh the UI
            }
        } else {
            logMessage = "Empty cell clicked at (" + x + ", " + y + ")";
            System.out.println(logMessage);
        }
        // Save the log message to the text file
        saveLog(logMessage);
    }

    // private void initializeSaveFile() {
    // try {
    // File saveDir = new File("src/resources/savedGames");
    // if (!saveDir.exists() && !saveDir.mkdirs()) {
    // throw new IOException("Failed to create directory: " +
    // saveDir.getAbsolutePath());
    // } else {
    // System.out.println("Directory created or already exists: " +
    // saveDir.getAbsolutePath());
    // }

    // File saveFile = new File(SAVE_FILE_PATH);
    // if (!saveFile.exists() && !saveFile.createNewFile()) {
    // throw new IOException("Failed to create file: " +
    // saveFile.getAbsolutePath());
    // } else {
    // System.out.println("File created or already exists: " +
    // saveFile.getAbsolutePath());
    // }
    // } catch (IOException e) {
    // System.err.println("Error initializing save file: " + e.getMessage());
    // }
    // }

    private void initializeSaveFile() {
        try {
            // Ensure the directory exists
            File saveDir = new File("resources/savedGames");
            if (!saveDir.exists() && !saveDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + saveDir.getAbsolutePath());
            } else {
                System.out.println("Directory created or already exists: " + saveDir.getAbsolutePath());
            }

            // Ensure the file exists and clear its content on startup
            File saveFile = new File(SAVE_FILE_PATH);
            if (!saveFile.exists()) {
                if (!saveFile.createNewFile()) {
                    throw new IOException("Failed to create file: " + saveFile.getAbsolutePath());
                } else {
                    System.out.println("File created: " + saveFile.getAbsolutePath());
                }
            } else {
                // Clear the content of the existing file (open in write mode)
                FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH, false); // 'false' to overwrite
                fileWriter.close(); // Close immediately after clearing
                System.out.println("File content cleared: " + saveFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error initializing save file: " + e.getMessage());
        }
    }

    private void saveLog(String logMessage) {
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH, true); // 'true' for appending
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(logMessage);
            writer.newLine(); // Add a newline after the log message
            // Flush and close the writer
            writer.flush(); // Ensure it's written to disk immediately
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to save file: " + e.getMessage());
        }
    }

    private void updateBoardView() {
        for (int i = 0; i < 8; i++) { // Loop over 8 rows
            for (int j = 0; j < 5; j++) { // Loop over 5 columns
                Piece piece = board.getPiece(i, j);
                view.updateCell(i, j, piece); // Update each cell on the view
            }
        }
    }
}