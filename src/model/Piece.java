package model;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class Piece {
    private String name;
    private String color;
    private ImageIcon image;
    int x, y;
    // private boolean isKilled;
    // private int x; // X-coordinate on the board
    // private int y; // Y-coordinate on the board

    // "Tor", "tor", "Red"
    public Piece(String name, String color, int x, int y) {
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
        this.image = loadImage();
        // this.isKilled = false;
    }

    // "Tor", "tor", "Red"
    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
        this.image = loadImage();
        // this.isKilled = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
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

    // polymorphic is used
    public void move(int toX, int toY, Board board) {
    }

    public abstract List<int[]> getAvailableMoves(int x, int y, Board board);
}