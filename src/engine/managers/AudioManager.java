package engine.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    public static final boolean SOUND_DISABLED = true;

    public static void play(String soundPath) {

        if (SOUND_DISABLED) {
            // System.err.println("Sound Disabled");
            return;
        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
