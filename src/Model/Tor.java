package Model;

public class Tor extends Piece {

    public Tor() {
        super("Tor", "Red"); // Hardcoded color "Gold" for the Tor piece
    }

    public Tor(String colorTurn) {
        super("Tor", "Red"); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Tor";
    }

    @Override
    public String getColor() {
        return "Gold";
    }
}
