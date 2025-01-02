package Model;

public class Xor extends Piece {
    public Xor() {
        super("Xor", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
    
    public Xor(String colorTurn) {
        super("Xor", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
        
    @Override
    public String getName() {
        return "Xor";
    }
        
    @Override
    public String getColor() {
        return "Gold";
    }
}
