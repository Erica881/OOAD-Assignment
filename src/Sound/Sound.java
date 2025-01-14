// package Sound;

// import javax.sound.sampled.*;
// import java.io.IOException;
// import java.io.InputStream;

// public class Sound {
//     private final InputStream move; // Use InputStream instead of URL
//     private final InputStream notify; // Use InputStream instead of URL
//     private final InputStream capture; // Use InputStream instead of URL

//     private boolean isMuted;
//     private Clip clip;

//     public Sound() {
//         // Load the move sound from the resources folder
//         this.move = getClass().getResourceAsStream("move.wav"); // Ensure the path is correct
//         this.notify = getClass().getResourceAsStream("notify.wav"); // Ensure the path is correct
//         this.capture = getClass().getResourceAsStream("capture.wav"); // Ensure the path is correct
//     }

//     public void soundMove() {
//         play(move);
//     }

//     public void soundNotify() {
//         play(notify);
//     }

//     public void soundCapture() {
//         play(capture);
//     }

//     public void mute(boolean mute) {
//         isMuted = mute;
//         if (mute) {
//             stop();
//         } else {
//             unmute();
//         }
//     }

//     public boolean isMuted() {
//         return isMuted;
//     }

//     public void unmute() {
//         if (!isMuted && clip != null) {
//             clip.setFramePosition(0); // Reset to the start
//             clip.start();
//         }
//     }

//     public void loop() {
//         if (!isMuted && clip != null) {
//             clip.loop(Clip.LOOP_CONTINUOUSLY);
//         }
//     }

//     public void stop() {
//         if (clip != null) {
//             clip.stop();
//         }
//     }

//     private void play(InputStream inputStream) {
//         try {
//             // Open the audio input stream from the InputStream
//             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//             Clip clip = AudioSystem.getClip();
//             clip.open(audioInputStream);
//             clip.addLineListener(event -> {
//                 if (event.getType() == LineEvent.Type.STOP) {
//                     clip.close();
//                 }
//             });
//             clip.start();
//         } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//             e.printStackTrace();
//         }
//     }
// }

package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private final InputStream move;
    private final InputStream notify;
    private final InputStream capture;

    private boolean isMuted;
    private Clip clip;

    public Sound() {
        // Load the sound files
        this.move = getClass().getResourceAsStream("move.wav");
        this.notify = getClass().getResourceAsStream("notify.wav");
        this.capture = getClass().getResourceAsStream("capture.wav");
    }

    public void soundMove() {
        if (!isMuted) { // Check if sound is muted before playing
            play(move);
        }
    }

    public void soundNotify() {
        if (!isMuted) { // Check if sound is muted before playing
            play(notify);
        }
    }

    public void soundCapture() {
        if (!isMuted) { // Check if sound is muted before playing
            play(capture);
        }
    }

    public void mute(boolean mute) {
        System.out.println("is muted?; " + mute);
        isMuted = mute;
        if (isMuted) {
            stop(); // Stop the sound if muted
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void unmute() {
        if (!isMuted && clip != null) {
            clip.setFramePosition(0); // Reset to the start
            clip.start();
        }
    }

    public void loop() {
        if (!isMuted && clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        System.out.println("where is clip " + clip);
        if (clip != null) {
            clip.stop();
            System.out.println("clip shoudl stop");
        }
    }

    private void play(InputStream inputStream) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
