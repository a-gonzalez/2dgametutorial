package unus.entity;

import unus.main.Game;

public class Axe extends Entity
{
    public Axe(Game game)
    {
        super(game);

        type = Type.Axe;
        down0 = setup("/resources/image/item/axe.png");
        description = String.format("[%s]\nUsed to cut down trees.", getClass().getSimpleName());
    }
}