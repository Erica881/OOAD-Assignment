package View;

import javax.swing.*;
import java.awt.*;
import Controller.GameController;

public class MainView {
    private JFrame frame;
    private BoardView boardView;
    private JPanel menuPanel;
    private JLabel statusLabel;
    private JButton startGameButton;

    private GameController controller; // Reference to the controller

    public MainView(GameController controller) {
        this.controller = controller; // Initialize the controller

        // Initialize the frame
        frame = new JFrame("Kwazam Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the board view (not displayed initially)
        boardView = new BoardView();

        // Initialize the menu panel with a button
        menuPanel = new JPanel();
        startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(200, 50));
        startGameButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Action listener for the start game button
        startGameButton.addActionListener(e -> startGame());

        // Add the button to the menu panel
        menuPanel.add(startGameButton);

        // Initialize the status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout for left alignment
        statusLabel = new JLabel("Welcome to the game!");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size
        statusPanel.add(statusLabel);

        // Add the status panel to the top (BorderLayout.NORTH)
        frame.add(statusPanel, BorderLayout.NORTH);

        // Add the menu panel to the frame
        frame.add(menuPanel, BorderLayout.CENTER);

        // Set a size for the frame, ensuring it's large enough to display the menu
        frame.setSize(400, 600);
        frame.setMinimumSize(new Dimension(600, 750)); // Set minimum width and

        frame.setVisible(true);
    }

    // Method to start the game when the button is clicked
    private void startGame() {
        // Remove the menu panel and show the board
        frame.getContentPane().remove(menuPanel); // Hide menu panel

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
