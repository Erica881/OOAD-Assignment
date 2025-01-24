package model;

import java.util.ArrayList;

public class Tor extends Piece {

    public Tor(String colorTurn) {
        super("Tor", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Tor";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
    }
}