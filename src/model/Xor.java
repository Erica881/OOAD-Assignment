package model;

import java.util.ArrayList;

public class Xor extends Piece {

    public Xor(String colorTurn) {
        super("Xor", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    public Xor(String colorTurn, int x, int y) {
        super("Xor", colorTurn, x, y); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Xor";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(int x, int y, Board board) {
        System.out.println("get available move in xor");
        return new ArrayList<>();
    }
}
