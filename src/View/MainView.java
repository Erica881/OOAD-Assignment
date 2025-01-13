package View;

import javax.swing.*;
import java.awt.*;
import Controller.GameController;

public class MainView {
    private JFrame frame;
    private BoardView boardView;
    private MenuView menuView;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private GameController controller;

    public MainView(GameController controller) {
        this.controller = controller; // Initialize the controller

        // Initialize the frame
        frame = new JFrame("Kwazam Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the board view (not displayed initially)
        boardView = new BoardView();

        // Initialize the menu view
        menuView = new MenuView(this);

        // Initialize the status panel
        statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusLabel = new JLabel("Welcome to the game!");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusPanel.add(statusLabel);

        // Add the status panel to the top (BorderLayout.NORTH)
        frame.add(statusPanel, BorderLayout.NORTH);

        // Add the menu panel to the center
        frame.add(menuView, BorderLayout.CENTER);

        // Set the frame size and visibility
        frame.setSize(400, 600);
        frame.setMinimumSize(new Dimension(600, 750));
        frame.setVisible(true);
    }

    // Delegation archieve -
    public void startGame() {
        // Remove the menu panel and show the board
        frame.getContentPane().remove(menuView); // Hide menu panel

        // Add the board view
        frame.add(boardView, BorderLayout.CENTER);

        // Revalidate and repaint to update the view
        frame.revalidate();
        frame.repaint();

        // Inform the controller that the game has started
        controller.startGame();
    }

    // Update the status label with game messages
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    // Display the view (already called when initialized)
    public void display() {
        frame.setVisible(true);
    }

    // Get the BoardView for interaction (can be used by controller)
    public BoardView getBoardView() {
        return boardView;
    }

}