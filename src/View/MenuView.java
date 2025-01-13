// package View;

// import javax.swing.*;
// import java.awt.*;

// public class MenuView extends JPanel {
//     private JButton startGameButton;

//     public MenuView(MainView mainView) {
//         // Set GridBagLayout
//         this.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();

//         // Initialize the start game button
//         startGameButton = new JButton("Start Game");
//         startGameButton.setFont(new Font("Arial", Font.PLAIN, 14));
//         startGameButton.setSize(200, 20);
//         // Configure GridBagConstraints for the button
//         gbc.gridx = 0; // Column index
//         gbc.gridy = 0; // Row index
//         gbc.weightx = 1.0; // Allow horizontal stretching
//         gbc.weighty = 1.0; // Allow vertical stretching
//         gbc.fill = GridBagConstraints.CENTER; // Center alignment
//         gbc.insets = new Insets(10, 10, 10, 10); // Padding

//         // Add the button to the panel
//         this.add(startGameButton, gbc);

//         // Add an ActionListener to the button
//         startGameButton.addActionListener(e -> mainView.startGame());
//     }
// }

package View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JButton startGameButton;

    public MenuView(MainView mainView) {
        // Set GridBagLayout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize the start game button
        startGameButton = new JButton("Start Game");

        // Adjust the size and font of the button
        startGameButton.setPreferredSize(new Dimension(150, 50)); // Larger size
        startGameButton.setFont(new Font("Arial", Font.BOLD, 15)); // Larger font

        // Configure GridBagConstraints for the button
        gbc.gridx = 0; // Column index
        gbc.gridy = 0; // Row index
        gbc.weightx = 1.0; // Allow horizontal stretching
        gbc.weighty = 1.0; // Allow vertical stretching
        gbc.fill = GridBagConstraints.CENTER; // Center alignment
        gbc.insets = new Insets(20, 20, 20, 20); // Extra padding

        // Add the button to the panel
        this.add(startGameButton, gbc);

        // Add an ActionListener to the button
        startGameButton.addActionListener(e -> mainView.startGame());
    }
}
