package unus.entity;

import unus.main.Game;

public class Sword extends Entity
{
    public Sword(Game game)
    {
        super(game);

        type = Type.Sword;
        attack_value = 2;
        attack_hitbox.width = 36;
        attack_hitbox.height = 36;
        description = String.format("[%s]\nBasic metal sword.\nAttack: %d", getClass().getSimpleName(), attack_value);
        down0 = setup("/resources/image/item/sword.png");
    }
}