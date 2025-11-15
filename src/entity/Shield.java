package unus.entity;

import unus.main.Game;

public class Shield extends Entity
{
    public Shield(Game game)
    {
        super(game);

        type = Type.Shield;
        defense_value = 1;
        description = String.format("[%s]\nBasic wooden buckler.\nDefense: %d", getClass().getSimpleName(), defense_value);
        down0 = setup("/resources/image/item/wood_shield.png");
    }
}