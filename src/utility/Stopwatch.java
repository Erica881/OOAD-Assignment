// package utility;

// import java.util.Timer;
// import java.util.TimerTask;

// public class Stopwatch {
//     private Timer timer;
//     private int seconds;
//     private boolean isRunning;
//     private GameTimerListener listener;

//     public Stopwatch(GameTimerListener listener) {
//         this.listener = listener;
//         this.timer = new Timer();
//         this.seconds = 0;
//         this.isRunning = false;
//     }

//     public void start() {
//         if (!isRunning) {
//             isRunning = true;
//             timer.scheduleAtFixedRate(new TimerTask() {
//                 @Override
//                 public void run() {
//                     seconds++;
//                     listener.onTimeUpdate(seconds);
//                 }
//             }, 1000, 1000); // Update every second
//         }
//     }

//     public void stop() {
//         if (isRunning) {
//             isRunning = false;
//             timer.cancel();
//         }
//     }

//     public void reset() {
//         seconds = 0;
//         listener.onTimeUpdate(seconds);
//     }

//     public int getElapsedTime() {
//         return seconds;
//     }

//     public interface GameTimerListener {
//         void onTimeUpdate(int seconds);
//     }
// }

package utility;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    private Timer timer;
    private TimerTask timerTask;
    private int seconds;
    private boolean isRunning;
    private GameTimerListener listener;

    public Stopwatch(GameTimerListener listener) {
        this.listener = listener;
        this.timer = new Timer();
        this.timerTask = null;
        this.seconds = 0;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;

            // Create a new TimerTask for resuming
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    listener.onTimeUpdate(seconds);
                }
            };

            timer.scheduleAtFixedRate(timerTask, 1000, 1000); // Update every second
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            timer.cancel();
        }
    }

    public void pause() {
        if (isRunning) {
            isRunning = false;

            // Cancel the current TimerTask to pause the timer
            if (timerTask != null) {
                timerTask.cancel();
                timerTask = null;
            }
        }
    }

    public void reset() {
        pause(); // Pause the timer before resetting
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
