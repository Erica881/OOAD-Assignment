package model;

public class Biz extends Piece {

    public Biz(String colorTurn) {
        super("Biz", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Biz";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }
}
