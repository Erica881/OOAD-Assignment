package model;

import java.util.List;

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

    @Override
    public List<int[]> getAvailableMoves(int x, int y, Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
    }
}
