package unus.tile;

import java.awt.image.BufferedImage;

public class Tile
{
    BufferedImage image;
    boolean solid;

    public Tile()
    {
        solid = false;
    }

    public boolean isSolid()
    {
        return solid;
    }
}