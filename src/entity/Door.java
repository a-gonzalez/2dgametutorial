package unus.entity;

import unus.main.Game;

public class Door extends Entity
{
    public Door(Game game)
    {
        super(game);

        this.type = Type.Key;
        this.down0 = setup("/resources/image/item/door.png");
        this.solid = true;
    }
}