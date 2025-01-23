package model;

public class Xor extends Piece {

    public Xor(String colorTurn) {
        super("Xor", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Xor";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }
}
