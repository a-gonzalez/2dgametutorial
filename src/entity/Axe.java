package unus.entity;

import unus.main.Game;

public class Axe extends Entity
{
    public Axe(Game game)
    {
        super(game);

        type = Type.Axe;
        attack_value = 2;
        attack_hitbox.width = 30;
        attack_hitbox.height = 30;
        down0 = setup("/resources/image/item/axe.png");
        description = String.format("[%s]\nWeapon / Tool.\nAttack: %d", getClass().getSimpleName(), attack_value);
    }
}