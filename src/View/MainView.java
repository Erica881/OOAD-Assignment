package View;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;
    private BoardView boardView;
    private JLabel statusLabel;

    public MainView() {
        // Initialize the main frame
        frame = new JFrame("Kwazam Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the board view
        boardView = new BoardView();
        frame.add(boardView, BorderLayout.CENTER);

        // Initialize the status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Welcome to the game!");
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);

        // Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }

    // Get the BoardView for interaction
    public BoardView getBoardView() {
        return boardView;
    }

    // Update the status label
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    // Display the view
    public void display() {
        frame.setVisible(true);
    }
}
