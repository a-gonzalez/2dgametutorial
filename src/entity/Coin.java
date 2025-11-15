package unus.entity;

import unus.main.Game;

public class Coin extends Entity
{
    public Coin(Game game)
    {
        super(game);

        type = Type.Item;
        down0 = setup("/resources/image/item/bronze_coin.png");
        description = String.format("[%s]\nGame currency.", getClass().getSimpleName());
    }
}