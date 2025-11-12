package unus.entity;

import unus.main.Game;

public class BlueShield extends Entity
{
    public BlueShield(Game game)
    {
        super(game);

        type = Type.Shield;
        defense_value = 3;
        down0 = setup("/resources/image/item/blue_shield.png");
    }
}