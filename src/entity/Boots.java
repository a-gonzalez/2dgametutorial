package unus.entity;

import unus.main.Game;

public class Boots extends Entity
{
    public Boots(Game game)
    {
        super(game);

        type = Type.Item;
        down0 = setup("/resources/image/item/boot.png");
        description = String.format("[%s]\nSpeed boost.", getClass().getSimpleName());
    }
}