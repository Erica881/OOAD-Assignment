package Model;

public class RamPiece extends Piece {

    public RamPiece() {
        super("Ram", "Gold"); // Hardcoded color "Gold" for the Ram piece
    }

    public RamPiece(String colorTurn) {
        super("Ram", "Gold"); // Hardcoded color "Gold" for the Ram piece
    }

    @Override
    public String getName() {
        return "Ram";
    }

    @Override
    public String getColor() {
        return "Gold";
    }
}
