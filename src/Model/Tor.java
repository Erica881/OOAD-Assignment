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

    }

    @Override
    public String getType() {
        return super.getType(); // Use the type from the base class
    }
}
