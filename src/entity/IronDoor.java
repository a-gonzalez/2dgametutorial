package unus.entity;

import unus.main.Game;

public class IronDoor extends Entity
{
    public IronDoor(Game game)
    {
        super(game);

        type = Type.Key;
        solid = true;
        down0 = setup("/resources/image/item/door_iron.png");
    }
}