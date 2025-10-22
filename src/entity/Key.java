package unus.entity;

import unus.main.Game;

public class Key extends Entity
{
    public Key(Game game)
    {
        super(game);

        this.type = Type.Key;
        this.down0 = setup("/resources/image/item/key.png");
    }
}