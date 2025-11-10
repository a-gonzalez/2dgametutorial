package unus.entity;

import unus.main.Game;

public class BasicShield extends Entity
{
    public BasicShield(Game game)
    {
        super(game);

        type = Type.Shield;
        defense_value = 1;
        down0 = setup("/resources/image/item/basic_shield.png");
    }
}