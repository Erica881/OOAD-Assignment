package Model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Piece {
    private String name;
    private String color;
    private ImageIcon image;

    // "Tor", "tor", "Red"
    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
        this.image = loadImage();
    }

    private ImageIcon loadImage() {
        // Construct the image path dynamically
        String imagePath = "/resources/" + name + color + ".png"; // Adjust path as needed
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage(); // Get the original image
            Image scaledImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Resize to fit the cell
            return new ImageIcon(scaledImg); // Return the resized image as an ImageIcon
        } catch (Exception e) {
            System.err.println("Image not found: " + imagePath);
            return null; // Return null or a placeholder if the image is not found
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ImageIcon getImage() {
        return image;
    }
    
    public void move(int currentX, int currentY, Piece[][] board) {
        
    }
}