package unus.entity;

import unus.main.Game;

public class Heart extends Entity
{
    public Heart(Game game)
    {
        super(game);

        type = Type.Heart;
        image = setup("/resources/image/item/heart/full.png");
        image1 = setup("/resources/image/item/heart/half.png");
        image2 = setup("/resources/image/item/heart/empty.png");
    }
}