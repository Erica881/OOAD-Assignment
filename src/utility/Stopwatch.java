package utility;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch {
    private Timer timer;
    private int seconds;
    private boolean isRunning;
    private GameTimerListener listener;

    // Listener Interface
    public interface GameTimerListener {
        void onTimeUpdate(int seconds);
    }

    // Constructor
    public Stopwatch(GameTimerListener listener) {
        this.listener = listener;
        this.seconds = 0;
        this.isRunning = false;

        // Initialize Timer
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (listener != null) {
                    listener.onTimeUpdate(seconds); // Notify listener of time update
                }
            }
        });
    }

    // Start the timer
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.start();
        }
    }

    // Stop the timer
    public void stop() {
        if (isRunning) {
            isRunning = false;
            timer.stop();
        }
    }

    // Reset the timer
    public void reset() {
        stop();
        seconds = 0;
        if (listener != null) {
            listener.onTimeUpdate(seconds); // Notify listener of reset
        }
    }

    // Get elapsed time in seconds
    public int getElapsedTime() {
        return seconds;
    }
}