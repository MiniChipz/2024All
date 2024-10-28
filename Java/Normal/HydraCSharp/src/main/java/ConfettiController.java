import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;

public class ConfettiController {
    private final String[] randomStrings = {
            "DO GUITAR",
            "PROGRAM C#"
    };

    private final Random random = new Random();

    public String handleButtonClick() {
        playHappySound();
        return getRandomString();
    }

    private String getRandomString() {
        return randomStrings[random.nextInt(randomStrings.length)];
    }

    private void playHappySound() {
        try {
            // Generate a simple beep sound
            byte[] buf = new byte[2];
            AudioFormat af = new AudioFormat(8000f, 8, 1, true, false);
            try (ByteArrayInputStream bais = new ByteArrayInputStream(buf)) {
                AudioInputStream ais = new AudioInputStream(bais, af, buf.length);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}