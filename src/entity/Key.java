package unus.entity;

import unus.main.Game;

public class Key extends Entity
{
    public Key(Game game)
    {
        super(game);

        type = Type.Item;
        down0 = setup("/resources/image/item/key.png");
        description = String.format("[%s]\nUsed to open locked doors.", getClass().getSimpleName());
    }
}