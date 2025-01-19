package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogManager {
    private static final String SAVE_FILE_PATH = "src/resources/savedGames/game_log.txt";

    public void initializeSaveFile() {
        File saveFile = new File(SAVE_FILE_PATH);
        try {
            if (!saveFile.exists())
                saveFile.createNewFile();
            new FileWriter(saveFile, false).close(); // Clear the file
            System.out.println("Log reseted.");
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
}
