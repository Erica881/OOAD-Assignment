package view;

import javax.swing.*;
import java.awt.*;

import Sound.Sound;
import controller.GameController;

public class MainView {
    private JFrame frame;
    private BoardView boardView;
    private MenuView menuView;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private GameController controller;
    private Sound sound;
    private JLabel soundIcon;

    private String unmute_icon_path = "/resources/image/unmute_icon.png";
    private String mute_icon_path = "/resources/image/mute_icon.png";

    public MainView(GameController controller) {
        sound = new Sound(controller);
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

        // soundIcon = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(unmute_icon_path));

        // Resize the image to a smaller size (e.g., 24x24)
        Image resizedImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        soundIcon = new JLabel(new ImageIcon(resizedImage));

        soundIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        soundIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setMute(!controller.getMuteStatus()); // Mute or unmute when clicked
            }
        });
        statusPanel.add(soundIcon);

        // Add the status panel to the top (BorderLayout.NORTH)
        frame.add(statusPanel, BorderLayout.NORTH);

        // Add the menu panel to the center
        frame.add(menuView, BorderLayout.CENTER);

        frame.setSize(400, 600);
        frame.setMinimumSize(new Dimension(600, 750));
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

    // Mute/unmute functionality for MenuView
    public void setMute(boolean mute) {
        controller.setMute(mute);
    }

    public void updateSoundIcon(boolean isMuted) {
        String iconPath = isMuted ? mute_icon_path : unmute_icon_path;
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));

        // Resize the image to a smaller size (e.g., 24x24)
        Image resizedImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        soundIcon.setIcon(new ImageIcon(resizedImage));
    }

    public Sound getSound() {
        return sound;
    }
}