package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LogManager {

    private static final String SAVE_FILE_PATH = "OOAD-Assignment/src/resources/savedGames/game_log.txt";

    public void initializeSaveFile() {
        File saveFile = new File(SAVE_FILE_PATH);
        try {
            if (!saveFile.exists())
                saveFile.createNewFile();
            new FileWriter(saveFile, false).close(); // Clear the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logAction(String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to save file: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    public void saveGame(String winningPlayer) {
        // Open the file save dialog to prompt the user where to save the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");

        // Optionally, set file filters
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));

        int result = fileChooser.showSaveDialog(null); // Open save dialog

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(".txt")) {
                filePath += ".txt"; // Add file extension if not specified
            }

            // Read content from the log file (SAVE_FILE_PATH) and write to the selected
            // file
            try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(winningPlayer + " won the game!\n");
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Game saved to: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving the game.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("File saving canceled.");
        }
    }
}
