package unus.entity;

import unus.main.Game;

public class MetalDoor extends Entity
{
    public MetalDoor(Game game)
    {
        super(game);

        type = Type.Door;
        solid = true;
        down0 = setup("/resources/image/item/metal_door.png");
    }
}