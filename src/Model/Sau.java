package Model;

public class Sau extends Piece {
    public Sau() {
        super("Sau", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
    
    public Sau(String colorTurn) {
        super("Sau", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
        
    @Override
    public String getName() {
        return "Sau";
    }
        
    @Override
    public String getColor() {
        return "Gold";
    }
}
