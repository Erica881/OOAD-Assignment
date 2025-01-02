package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece {
    private String type; // Type of the piece, e.g., "Tor" or "Ram"
    private Player color; // Color of the piece, e.g., RED or BLUE
    private ImageIcon image; // Image associated with the piece

    public Piece(String type, Player color) {
        this.type = type;
        this.color = color;
        this.image = loadImage(); // Load the image when the piece is created
    }

    // private ImageIcon loadImage() {
    // // Construct the image path dynamically

    // String imagePath = "/resources/" + type + color.name() + ".png"; // Adjust
    // path as needed
    // try {
    // return new ImageIcon(getClass().getResource(imagePath));
    // } catch (Exception e) {
    // System.err.println("Image not found: " + imagePath);
    // return null; // Return null or a placeholder if the image is not found
    // }
    // }

    private ImageIcon loadImage() {
        // Construct the image path dynamically
        String imagePath = "/resources/" + type + color.name() + ".png"; // Adjust path as needed
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

    public Player getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void move() {
    };
}
