package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class OpenedChest extends Item
{
    public OpenedChest(Game game)
    {
        super(game);

        this.type = Type.Chest;
        this.solid = true;

        getImage();
    }

    private void getImage()
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/chest_opened.png"));
            util.scale(image, game.TILE_SIZE, game.TILE_SIZE);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}