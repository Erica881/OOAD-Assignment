// package Model;

// public class Tor extends Piece {

//     public Tor(Player color) {
//         super(“Tor”, color); // Hardcoded color "Gold" for the Tor piece
//     }

//     @Override
//     public String getName() {
//         return "Tor";
//     }

//     @Override
//     public Player getColor() {
//         return color;
//     }
// }

package Model;

public class Tor extends Piece {

    public Tor(Player color) {
        super("Tor", color); // Pass "Tor" as type and the Player color

    public Tor(String colorTurn) {
        super("Tor", colorTurn); // Hardcoded color "Gold" for the Tor piece
    }

    @Override
    public String getName() {
        return "Tor";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }
}
