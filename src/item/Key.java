package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class Key extends Item
{
    public Key(Game game)
    {
        super(game);

        this.type = Type.Key;
        this.solid = true;

        getImage();
    }

    private void getImage()
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/key.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}