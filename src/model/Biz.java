package model;

import java.util.ArrayList;

public class Biz extends Piece {

    public Biz(String colorTurn) {
        super("Biz", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Biz(String colorTurn, int x, int y) {
        super("Biz", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Biz";
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
