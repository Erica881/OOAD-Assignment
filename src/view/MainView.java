package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import controller.GameController;

public class MainView {
    private BoardView boardView;
    private MenuView menuView;
    private SettingView settingView;
    private JFrame frame;
    private JPanel statusPanel, iconPanel, currentView;
    private JLabel statusLabel, timeLabel;
    private GameController controller;
    private JLabel soundIcon, settingIcon;

    private String unmute_icon_path = "/resources/image/unmute_icon.png";
    private String mute_icon_path = "/resources/image/mute_icon.png";

    public MainView(GameController controller) {
        this.controller = controller; // Initialize the controller

        // Initialize the frame
        frame = new JFrame("Kwazam Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the board view (not displayed initially)
        boardView = new BoardView();
        menuView = new MenuView(this);
        settingView = new SettingView(this);

        // Add the initial view (MenuView) to the frame
        currentView = menuView;
        frame.add(currentView, BorderLayout.CENTER);

        // Initialize the status panel
        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Welcome to the game!");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Initialize the time panel
        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timeLabel = new JLabel("Time: 00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setBorder(new EmptyBorder(0, 20, 0, 30)); // Top, Left, Bottom, Right
        statusPanel.setBorder(new EmptyBorder(10, 0, 10, 20)); // Top, Left, Bottom, Right

        statusPanel.add(timeLabel);
        statusPanel.add(statusLabel);

        iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
        iconPanel.add(soundIcon);

        // Add Settings Icon
        ImageIcon originalSettingIcon = new ImageIcon(getClass().getResource("/resources/image/setting.png"));

        // Resize the image to a smaller size (e.g., 24x24)
        Image resizedSettingImage = originalSettingIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        settingIcon = new JLabel(new ImageIcon(resizedSettingImage));
        settingIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // open setting view
                showSettingView();
            }
        });
        iconPanel.add(settingIcon);

        // Initialize a container panel for the top bar
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.add(statusPanel, BorderLayout.WEST);
        topBarPanel.add(iconPanel, BorderLayout.EAST);
        frame.add(topBarPanel, BorderLayout.NORTH);

        // Add the menu panel to the center
        frame.add(menuView, BorderLayout.CENTER);

        frame.setSize(400, 600);
        frame.setMinimumSize(new Dimension(600, 750));
        frame.setMinimumSize(new Dimension(600, 750));
        frame.setVisible(true);
    }

    public void showSettingView() {
        controller.stopTimer();
        frame.remove(currentView); // Remove the current view
        currentView = settingView; // Update the current view to SettingView
        frame.add(currentView, BorderLayout.CENTER); // Add the SettingView
        frame.revalidate(); // Refresh the frame
        frame.repaint();
    }

    // Delegation archieve -
    public void startGame() {
        frame.remove(currentView); // Remove the current view
        currentView = boardView; // Update the current view to SettingView
        frame.add(currentView, BorderLayout.CENTER); // Add the SettingView
        frame.revalidate(); // Refresh the frame
        frame.repaint();

        // Inform the controller that the game has started
        controller.startGame();
    }

    // Update the time label with the formatted time
    public void updateTimeLabel(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        timeLabel.setText(String.format("Time: %02d:%02d", minutes, remainingSeconds));
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

    public void resumeGame() {
        // Update the UI if needed (e.g., hide settings view and show the game board)
        frame.getContentPane().remove(currentView); // Remove the current view (e.g., settings)
        frame.add(boardView, BorderLayout.CENTER); // Add the main game board view
        currentView = boardView; // Update the current view reference

        frame.revalidate();
        frame.repaint();

        // controller.resumeGame();
    }

}