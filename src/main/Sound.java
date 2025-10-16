package unus.main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
    private Clip clip;
    private URL[] url;

    public Sound()
    {
        initialize();
    }

    private void initialize()
    {
        url = new URL[10];

        url[0] = getClass().getResource("/resources/audio/theme.wav");
        url[1] = getClass().getResource("/resources/audio/coin.wav");
        url[2] = getClass().getResource("/resources/audio/unlock.wav");
        url[3] = getClass().getResource("/resources/audio/powerup.wav");
        url[4] = getClass().getResource("/resources/audio/fanfare.wav");
        url[5] = getClass().getResource("/resources/audio/dooropen.wav");
    } // TODO: use some sort of enum instead of number

    public void play()
    {
        clip.start();
    }

    public void stop()
    {
        clip.stop();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setSound(int index)
    {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(url[index]);
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}