package Model;

public class Biz extends Piece {
    public Biz() {
        super("Biz", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
    
    public Biz(String colorTurn) {
        super("Biz", "Red"); // Hardcoded color "Gold" for the Tor piece
    }
        
    @Override
    public String getName() {
        return "Biz";
    }
        
    @Override
    public String getColor() {
        return "Gold";
    }
}
