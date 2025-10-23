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
    }
}