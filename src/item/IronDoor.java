package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class IronDoor extends Item
{
    public IronDoor(Game game)
    {
        super(game);

        this.type = Type.IronDoor;
        this.solid = true;

        getImage();
    }

    private void getImage()
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/door_iron.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}