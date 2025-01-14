// package View;

// import javax.swing.*;
// import java.awt.*;

// public class MenuView extends JPanel {
//     private JButton startGameButton;
//     private JCheckBox muteCheckBox;

//     public MenuView(MainView mainView) {
//         // Set GridBagLayout
//         this.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();

//         // Initialize the start game button
//         startGameButton = new JButton("Start Game");

//         // Adjust the size and font of the button
//         startGameButton.setPreferredSize(new Dimension(150, 50)); // Larger size
//         startGameButton.setFont(new Font("Arial", Font.BOLD, 15)); // Larger font
//         // Add an ActionListener to the button
//         startGameButton.addActionListener(e -> mainView.startGame());

//         // Mute/Unmute Checkbox
//         muteCheckBox = new JCheckBox("Mute Sound");
//         muteCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
//         muteCheckBox.addActionListener(e -> mainView.setMute(muteCheckBox.isSelected())); // Notify MainView

//         // Configure GridBagConstraints for the button
//         gbc.gridx = 0; // Column index
//         gbc.gridy = 0; // Row index
//         gbc.weightx = 1.0; // Allow horizontal stretching
//         gbc.weighty = 1.0; // Allow vertical stretching
//         gbc.fill = GridBagConstraints.CENTER; // Center alignment
//         gbc.insets = new Insets(20, 20, 20, 20); // Extra padding

//         // Add the button to the panel
//         this.add(startGameButton, gbc);

//     }
// }

package View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JButton startGameButton;
    private JCheckBox muteCheckBox;

    public MenuView(MainView mainView) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        startGameButton = new JButton("Start Game");

        // Adjust the size and font of the button
        startGameButton.setPreferredSize(new Dimension(150, 50));
        startGameButton.setFont(new Font("Arial", Font.BOLD, 15));

        startGameButton.addActionListener(e -> mainView.startGame());

        // Mute/Unmute Checkbox
        muteCheckBox = new JCheckBox("Mute Sound");
        muteCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // ActionListener to update mute status when checkbox is clicked
        muteCheckBox.addActionListener(e -> {
            boolean isMuted = muteCheckBox.isSelected(); // Get mute checkbox state
            mainView.setMute(isMuted); // Pass the mute status to MainView
        });

        // Configure GridBagConstraints for the button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        this.add(startGameButton, gbc);

        // Configure GridBagConstraints for the checkbox
        gbc.gridy = 1; // Move the checkbox below the start button
        this.add(muteCheckBox, gbc);
    }
}
