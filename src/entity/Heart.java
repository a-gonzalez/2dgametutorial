package unus.entity;

import unus.main.Game;

public class Heart extends Entity
{
    public Heart(Game game)
    {
        super(game);

        this.type = Type.Heart;
        this.image = setup("/resources/image/item/heart_full.png");
        this.image1 = setup("/resources/image/item/heart_half.png");
        this.image2 = setup("/resources/image/item/heart_empty.png");
    }
}