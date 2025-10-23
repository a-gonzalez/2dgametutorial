package unus.entity;

import unus.main.Game;

public class Door extends Entity
{
    public Door(Game game)
    {
        super(game);

        type = Type.Key;
        solid = true;
        down0 = setup("/resources/image/item/door.png");
    }
}