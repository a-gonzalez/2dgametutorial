package unus.entity;

import unus.main.*;

public class RedPotion extends Entity
{
    Game game;
    int value = 5;

    public RedPotion(Game game)
    {
        super(game);

        this.game = game;
        type = Type.Item;
        down0 = setup("/resources/image/item/red_potion.png");
        description = String.format("[%s]\nHealing potion.\nDamage Restore: %d", getClass().getSimpleName(), value);
    }

    public void use(Entity entity)
    {
        game.state = State.Dialoque;
        game.ui.dialoque = String.format("You drank the potion!\nDamage restored by %d points.", value);

        entity.life += value;

        if (game.player.life > game.player.life_max)
        {
            game.player.life = game.player.life_max;
        }
        game.playSE(3);
    }
}