package unus.entity;

import unus.main.Game;

public class Chest extends Entity
{
    public Chest(Game game)
    {
        super(game);

        this.type = Type.Chest;
        this.down0 = setup("/resources/image/item/chest.png");
    }
}