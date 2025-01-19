package view;

import java.awt.*;

import javax.swing.*;

public class SettingView extends JPanel {
    private JButton stopGameButton, resetGameButton, resumeGameButton;

    public SettingView(MainView mainView) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Stop Game button
        resumeGameButton = new JButton("Resume Game");
        resumeGameButton.setPreferredSize(new Dimension(150, 50));
        resumeGameButton.setFont(new Font("Arial", Font.BOLD, 15));
        resumeGameButton.addActionListener(e -> mainView.resumeGame());

        // Adjust the size and font of the button
        resetGameButton = new JButton("Reset Game");
        resetGameButton.setPreferredSize(new Dimension(150, 50));
        resetGameButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetGameButton.addActionListener(e -> mainView.resetGame());

        // Stop Game button
        stopGameButton = new JButton("Stop Game");
        stopGameButton.setPreferredSize(new Dimension(150, 50));
        stopGameButton.setFont(new Font("Arial", Font.BOLD, 15));
        stopGameButton.addActionListener(e -> mainView.stopGame());

        // Configure GridBagConstraints for the button
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        this.add(resumeGameButton, gbc);
        gbc.gridy++;
        this.add(stopGameButton, gbc);

        // Configure GridBagConstraints for the checkbox
        gbc.gridy++;
        this.add(resetGameButton, gbc);
    }
}
