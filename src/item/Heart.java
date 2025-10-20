package unus.item;

import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class Heart extends Item
{
    public Heart(Game game)
    {
        super(game);

        initialize();
    }

    private void initialize()
    {
        this.type = Type.Heart;
        this.solid = true;

        setImages();
    }

    private void setImages()
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/heart_full.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/heart_half.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/image/item/heart_empty.png"));
            image = util.scale(image, game.TILE_SIZE, game.TILE_SIZE);
            image1 = util.scale(image1, game.TILE_SIZE, game.TILE_SIZE);
            image2 = util.scale(image2, game.TILE_SIZE, game.TILE_SIZE);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}