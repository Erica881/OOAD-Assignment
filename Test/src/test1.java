import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.util.HashMap;

public class test1 implements ChangeListener, KeyListener {
    Synthesizer synth;
    MidiChannel[] mChannels;
    int keypress;

    private HashMap<Character, Integer> pianoMap = new HashMap<Character, Integer>(); // Map keyboard keys to MIDI notes
    String[] whiteNotes = { "60", "62", "64", "65", "67", "69", "71" }; // C4 to B4
    String[] blackNotes = { "61", "63", " ", "66", "68", "70" }; // C#4 to A#4
    private char[] pianoKeys = { 'z', 's', 'x', 'd', 'c', 'v', 'g', 'b', 'h', 'n', 'j', 'm' }; // keys with sequence

    JButton[] w = new JButton[7];
    JButton[] b = new JButton[6]; // 5 black keys
    boolean isPlaying = false; // To track which notes are currently being played

    public test1() {
        // Initialize MIDI synthesizer
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mChannels = synth.getChannels();
        } catch (MidiUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Unable to open MIDI.");
        }

        // Initialize piano key mapping
        // pianoMap = new HashMap<>();
        initHashMap();

        // Create JFrame and JPanel
        JFrame frame = new JFrame("Pea-Air-Know");
        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        // Create and place white keys
        for (int i = 0; i < 7; i++) {
            w[i] = new JButton();
            w[i].setBackground(Color.WHITE);
            w[i].setBounds(i * 70, 0, 70, 300); // White keys positioning
            w[i].setName(String.valueOf(whiteNotes[i])); // Assign MIDI note as name
            w[i].addChangeListener(this);
            w[i].addKeyListener(this);
            panel.add(w[i], 0, -1);
        }

        // black keys GUI
        for (int i = 0; i < 6; i++) {
            if (i == 2)
                continue;
            b[i] = new JButton();
            b[i].setBackground(Color.BLACK);
            b[i].setLocation(35 + i * 70, 0);
            b[i].setSize(70, 150);
            b[i].setName(String.valueOf(blackNotes[i])); // Assign MIDI note as name
            b[i].addChangeListener(this);
            b[i].addKeyListener(this);
            panel.add(b[i], 1, -1);
        }

        // Add KeyListener for keyboard input
        // frame.addKeyListener(this);
        // frame.setFocusable(true); // Ensure the frame listens for key presses
        // frame.requestFocusInWindow(); // Request focus for key events

        // Set up the frame
        frame.setSize(500, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    // Initialize HashMap with key-to-note mapping
    private void initHashMap() {
        // // White keys mapping
        // for (int i = 0; i < whiteNotes.length; i++) {
        // pianoMap.put(pianoKeys[i], 60 + i); // Map keyboard key to corresponding MIDI
        // note for white keys
        // }

        // // Black keys mapping
        // for (int i = 0; i < blackNotes.length; i++) {
        // pianoMap.put(blackKeys[i], blackNotes[i]); // Map keyboard key to
        // corresponding MIDI note for black keys
        // }
        for (int i = 60; i <= 71; i++)
            pianoMap.put(pianoKeys[i - 60], i);
    }

    // Handle button state changes
    @Override
    public void stateChanged(ChangeEvent e) {
        // ButtonModel model = (ButtonModel) e.getSource();
        JButton button = (JButton) e.getSource();
        String btnName = button.getName();
        if (button.getModel().isPressed()) {
            System.out.println("Button Pressed: " + btnName);
            isPlaying = true;
            keypress = Integer.parseInt(btnName);
            mChannels[0].noteOn(keypress, 127);
        } else {
            if (isPlaying) {
                mChannels[0].noteOff(keypress);
            }
            isPlaying = false;
        }

        // if (button != null) {
        // int note = Integer.parseInt(button.getName());
        // if (model.isPressed() && !playingKeys[note]) { // Play note on button press
        // mChannels[0].noteOn(note, 127);
        // playingKeys[note] = true;
        // System.out.println("Button Pressed: Note ON " + note);
        // } else if (!model.isPressed() && playingKeys[note]) { // Stop note on button
        // release
        // mChannels[0].noteOff(note);
        // playingKeys[note] = false;
        // }
        // }
    }

    // Handle key press events
    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        Integer note = pianoMap.get(key);
        System.out.println("Key Pressed: " + key + ", Note ON: " + note);
        if (!isPlaying) { // Check if the note is not already being played
            isPlaying = true;
            keypress = pianoMap.get(key);
            mChannels[0].noteOn(note, 127); // Play the note
        }
    }

    // Handle key release events
    @Override
    public void keyReleased(KeyEvent e) {
        mChannels[0].noteOff(keypress);
        isPlaying = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new test1();
    }
}
