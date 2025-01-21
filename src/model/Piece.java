package model;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Piece {
    private String name;
    private String color;
    private ImageIcon image;
    private boolean isFlipped = false;
    private int x; // Current row of the piece
    private int y; // Current column of the piece

    // "Tor", "Red", (7, b)
    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
        this.image = loadImage();
    }

    public Piece(String name, String color, int x, int y) {
        this.name = name;
        this.color = color;
        this.image = loadImage();
        this.x = x;
        this.y = y;
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

    // Getter for the X position
    public int getX() {
        return x;
    }

    // Getter for the Y position
    public int getY() {
        return y;
    }

    // Method to set the position of the piece
    // public void setPosition(int x, int y) {
    // this.x = x;
    // this.y = y;
    // }

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

    public String formatCoordinate(int x, int y, boolean isFlipped) {
        char columnLetter = (char) ('a' + y); // Convert column index to letter
        int rowNumber = isFlipped ? 8 - x : x + 1; // Adjust row number for flipped board
        return "(" + rowNumber + ", " + columnLetter + ")";
    }

    public abstract List<int[]> getAvailableMoves(int x, int y, Board board); // Abstract method for getting moves

    public void move(int toX, int toY, Board board) {
        System.out.println("Moving from: (" + getX() + ", " + getY() + ") to: (" +
                toX + ", " + toY + ")");
        board.setPiece(getX(), getY(), null);
        board.setPiece(toX, toY, this);
        System.out.println("Successfully Moved to: (" + toX + ", " + toY + ")");
    }

    public boolean getFlipped() {
        return isFlipped;
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

}