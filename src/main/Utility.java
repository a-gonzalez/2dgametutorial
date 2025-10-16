package unus.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility
{
    public BufferedImage scale(BufferedImage original, int width, int height)
    {
        BufferedImage scaled = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaled.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();

        return scaled;
    }
}