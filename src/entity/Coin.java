package unus.entity;

import unus.main.Game;

public class Coin extends Entity
{
    public Coin(Game game)
    {
        super(game);

        type = Type.Coin;
        down0 = setup("/resources/image/item/coin_bronze.png");
        description = String.format("[%s]\nGame currency.", getClass().getSimpleName());
    }
}