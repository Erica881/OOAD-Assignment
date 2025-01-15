// package utility;

// import javax.swing.Timer;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class Stopwatch {
// private int elapsedTime = 0; // Time in seconds
// private Timer timer;

// public Stopwatch() {
// timer = new Timer(1000, new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// elapsedTime++;
// }
// });
// }

// // Starts the stopwatch
// public void start() {
// elapsedTime = 0;
// timer.start();
// }

// // Stops the stopwatch
// public void stop() {
// timer.stop();
// }

// // Resets the stopwatch
// public void reset() {
// stop();
// elapsedTime = 0;
// }

// // Returns the elapsed time in seconds
// public int getElapsedTime() {
// return elapsedTime;
// }

// // Returns the elapsed time as a formatted string (MM:SS)
// public String getFormattedTime() {
// int minutes = elapsedTime / 60;
// int seconds = elapsedTime % 60;
// return String.format("%02d:%02d", minutes, seconds);
// }
// }

package utility;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    private Timer timer;
    private int seconds;
    private boolean isRunning;
    private GameTimerListener listener;

    public Stopwatch(GameTimerListener listener) {
        this.listener = listener;
        this.timer = new Timer();
        this.seconds = 0;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    listener.onTimeUpdate(seconds);
                }
            }, 1000, 1000); // Update every second
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            timer.cancel();
        }
    }

    public void reset() {
        seconds = 0;
        listener.onTimeUpdate(seconds);
    }

    public int getElapsedTime() {
        return seconds;
    }

    public interface GameTimerListener {
        void onTimeUpdate(int seconds);
    }
}
