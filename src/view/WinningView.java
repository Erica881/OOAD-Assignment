package view;

public class WinningView extends MenuView {

    // Inherited fields and methods from MenuView

    // Additional fields and methods specific to WinningView
    private String winningMessage;

    public WinningView(MainView mainView) {
        super(mainView);
        System.out.println("WinningView");
        this.winningMessage = "Congratulations! You won the game!";
    }

    public String getWinningMessage() {
        return winningMessage;
    }
}