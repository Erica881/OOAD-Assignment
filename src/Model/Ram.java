package Model;

public class Ram extends Piece {
    public Ram() {
        super("Ram", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
    
    public Ram(String colorTurn) {
        super("Ram", "Red"); // Hardcoded color "Gold" for the Tor piece
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
