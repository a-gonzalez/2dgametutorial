package unus.entity;

import unus.main.Game;

public class Chest extends Entity
{
    public Chest(Game game)
    {
        super(game);

        solid = true;
        type = Type.Chest;
        down0 = setup("/resources/image/item/chest.png");

        hitbox.x = 3;
        hitbox.y = 6;
        hitbox.width = 42;
        hitbox.height = 42;
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
    }
}