package unus.entity;

import unus.main.Game;

public class Pick extends Entity
{
    public Pick(Game game)
    {
        super(game);

        type = Type.Pick;
        down0 = setup("/resources/image/item/pick.png");
        description = String.format("[%s]\nUsed to break rocks.", getClass().getSimpleName());
    }
}