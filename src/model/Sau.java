package model;

import java.util.ArrayList;

public class Sau extends Piece {

    public Sau(String colorTurn) {
        super("Sau", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Sau(String colorTurn, int x, int y) {
        super("Sau", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Sau";
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
