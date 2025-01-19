package model;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Piece {
    private String name;
    private String color;
    private ImageIcon image;
    private boolean isFlipped = false;
    // private boolean isKilled;
    // private int x; // X-coordinate on the board
    // private int y; // Y-coordinate on the board

    // "Tor", "tor", "Red"
    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
        this.image = loadImage();
        // this.isKilled = false;
    }

    private ImageIcon loadImage() {
        // Construct the image path dynamically
        String imagePath = "/resources/image/" + name + color + ".png"; // Adjust path as needed
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage(); // Get the original image

            // Get the size of the button
            int cellSize = 45; // Set a smaller size than cell that defined in boardview (50px)

            // Resize the image to fit the button size while maintaining aspect ratio
            Image scaledImg = img.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
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

    public String getNameNColour() {
        return name + " (" + color + ")";
    }

    public ImageIcon getImage() {
        return image;
    }

    // public void setKilled(boolean isKill) {
    // this.isKilled = isKill;
    // }
    //
    // not sure what to set, not using yet but might use in future

    public void move(int currentX, int currentY, Board board) {

    }

    public void rotateImage() {
        try {
            String imagePath = "/resources/image/" + name + color + ".png";
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage flippedImage = new BufferedImage(width, height, originalImage.getType());
            if (!isFlipped) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                    flippedImage.setRGB(x, height - 1 - y, originalImage.getRGB(x, y));
                    }
                }
            } else { // Use the original image (unflipped)
                flippedImage = originalImage;
            }

            // Resize the flipped image to fit the button size
            int cellSize = 45; // Match the size defined in loadImage
            Image scaledFlippedImage = flippedImage.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);

            this.image = new ImageIcon(scaledFlippedImage);
            isFlipped = !isFlipped; // Toggle the state
        } catch (Exception e) {
            System.err.println("Error flipping image: " + e.getMessage());
        }
    }

    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        // print the available move for the piece
        return new ArrayList<>();
    }
}