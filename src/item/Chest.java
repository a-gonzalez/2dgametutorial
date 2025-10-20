package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class Chest extends Item
{
    public Chest(Game game)
    {
        super(game);

        initialize();
    }

    private void initialize()
    {
        this.type = Type.Chest;
        this.solid = true;
        
        setImage();
    }

    private void setImage()
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/chest.png"));
            util.scale(image, game.TILE_SIZE, game.TILE_SIZE);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}