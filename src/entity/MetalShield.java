package unus.entity;

import unus.main.Game;

public class MetalShield extends Entity
{
    public MetalShield(Game game)
    {
        super(game);

        type = Type.Shield;
        defense_value = 3;
        description = String.format("[%s]\nA metal buckler.\nDefense: %d", getClass().getSimpleName(), defense_value);
        down0 = setup("/resources/image/item/blue_shield.png");
    }
}