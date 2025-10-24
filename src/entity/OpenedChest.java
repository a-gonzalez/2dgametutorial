package unus.entity;

import unus.main.Game;

public class OpenedChest extends Entity
{
    public OpenedChest(Game game)
    {
        super(game);

        type = Type.ChestOpened;
        solid = true;
        down0 = setup("/resources/image/item/chest_opened.png");

        hitbox.x = 3;
        hitbox.y = 6;
        hitbox.width = 42;
        hitbox.height = 42;
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
    }
}