package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView {
    private JFrame frame;
    private JButton startGameButton;

    public MenuView() {
        // Initialize the frame
        frame = new JFrame("Kwazam Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the start game button
        startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(200, 50));
        startGameButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Action listener for the start game button
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Add the button to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Set the window size and display
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void startGame() {
        // When the "Start Game" button is clicked, hide the menu and show the board
        frame.setVisible(false); // Hide MenuView

        // statusPanel.add(statusLabel, BorderLayout.CENTER);
        // Create and show the BoardView
        // new MainView().display(); // This is where the BoardView will show
    }
}
