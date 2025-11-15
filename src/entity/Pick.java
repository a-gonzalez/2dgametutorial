package unus.entity;

import unus.main.Game;

public class Pick extends Entity
{
    public Pick(Game game)
    {
        super(game);

        type = Type.Pick;
        attack_value = 2;
        attack_hitbox.width = 30;
        attack_hitbox.height = 30;
        down0 = setup("/resources/image/item/pick.png");
        description = String.format("[%s]\nWeapon / Tool.\nAttack: %d", getClass().getSimpleName(), attack_value);
    }
}