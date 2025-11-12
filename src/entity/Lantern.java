package unus.entity;

import unus.main.Game;

public class Lantern extends Entity
{
    public Lantern(Game game)
    {
        super(game);

        type = Type.Lantern;
        down0 = setup("/resources/image/item/lantern.png");
        description = String.format("[%s]\nIt lights the way.", getClass().getSimpleName());
    }
}