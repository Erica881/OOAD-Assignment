// package Sound;

// import java.net.URL;

// import javax.sound.sampled.*;

// public class Sound {
//     private final URL move;
//     // private final URL kill;
//     // private final URL error;

//     public Sound() {
//         this.move = this.getClass().getClassLoader().getResource("move.wav");
//         // this.kill = this.getClass().getClassLoader().getResource("move.wav");
//         // this.error = this.getClass().getClassLoader().getResource("move.wav");
//     }

//     public void soundMove() {
//         play(move);
//     }

//     private void play(URL url) {
//         try {
//             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
//             System.out.println(this.getClass().getClassLoader().getResource("move.wav"));

//             Clip clip = AudioSystem.getClip();
//             clip.open(audioInputStream);
//             clip.addLineListener(new LineListener() {
//                 @Override
//                 public void update(LineEvent event) {
//                     if (event.getType() == LineEvent.Type.STOP) {
//                         clip.close();
//                     }
//                 }
//             });
//             // audioInputStream.close();
//             clip.start();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private final InputStream move; // Use InputStream instead of URL
    private final InputStream notify; // Use InputStream instead of URL
    private final InputStream capture; // Use InputStream instead of URL

    public Sound() {
        // Load the move sound from the resources folder
        this.move = getClass().getResourceAsStream("move.wav"); // Ensure the path is correct
        this.notify = getClass().getResourceAsStream("notify.wav"); // Ensure the path is correct
        this.capture = getClass().getResourceAsStream("capture.wav"); // Ensure the path is correct
    }

    public void soundMove() {
        play(move);
    }

    public void soundNotify() {
        play(notify);
    }

    public void soundCapture() {
        play(capture);
    }

    private void play(InputStream inputStream) {
        try {
            // Open the audio input stream from the InputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
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
