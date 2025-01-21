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

    public void move(int toX, int currentY, Board board) {

    }

    // public List<int[]> getAvailableMoves(int x, int y, Board board) {

    // List<int[]> availableMoves = new ArrayList<>();
    // // Example: Ram can move one square forward or backward
    // int forward = x - 1; // Move forward
    // int backward = x + 1; // Move backward

    // if (forward >= 0 && forward < board.getBoard().length) {
    // if (board.getPiece(forward, y) == null) { // No piece in the target cell
    // availableMoves.add(new int[] { forward, y });
    // }
    // }
    // if (backward >= 0 && backward < board.getBoard().length) {
    // if (board.getPiece(backward, y) == null) {
    // availableMoves.add(new int[] { backward, y });
    // }
    // }

    // return availableMoves;
    // }

    public abstract List<int[]> getAvailableMoves(int x, int y, Board board); // Abstract method for getting moves
}