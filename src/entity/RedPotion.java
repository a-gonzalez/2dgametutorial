package unus.entity;

import unus.main.Game;

public class RedPotion extends Entity
{
    public RedPotion(Game game)
    {
        super(game);

        type = Type.Potion;
        down0 = setup("/resources/image/item/red_potion.png");
        description = String.format("[%s]\nHealing potion.", getClass().getSimpleName());
    }
}