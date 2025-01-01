package Model;

public enum Player {
    RED, BLUE;

    public Player next() {
        return (this == RED) ? BLUE : RED; // Toggle between RED and BLUE
    }
}
