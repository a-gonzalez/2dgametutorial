package unus.entity;

import unus.main.Game;

public class BasicSword extends Entity
{
    public BasicSword(Game game)
    {
        super(game);

        type = Type.Sword;
        attack_value = 2;
        down0 = setup("/resources/image/item/basic_sword.png");
    }
}