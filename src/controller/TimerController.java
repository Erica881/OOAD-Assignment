package controller;

import utility.Stopwatch;
import utility.Stopwatch.GameTimerListener;
import view.MainView;

public class TimerController implements GameTimerListener {
    private Stopwatch stopwatch;
    private MainView mainView; // The main view

    public TimerController(MainView mainView) {
        this.mainView = mainView; // Initialize mainView

        stopwatch = new Stopwatch(this);
    }

    // Stops the timer
    public void stopTimer() {
        stopwatch.stop();
    }

    // Method from the GameTimerListener interface to update the view
    @Override
    public void onTimeUpdate(int seconds) {
        // Inform the view to update the time label with the current seconds
        mainView.updateTimeLabel(seconds);
    }

    public void startTimer() {
        stopwatch.start();
    }

    public void resetTimer() {
        stopwatch.reset();
        stopwatch.start();
    }
}
