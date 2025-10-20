package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class IronDoor extends Item
{
    public IronDoor(Game game)
    {
        super(game);

        initialize();
    }

    private void initialize()
    {
        this.type = Type.IronDoor;
        this.solid = true;

        setImage();
    }

    private void setImage()
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/door_iron.png"));
            util.scale(image, game.TILE_SIZE, game.TILE_SIZE);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}