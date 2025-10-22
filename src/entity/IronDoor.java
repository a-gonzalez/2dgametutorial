package unus.entity;

import unus.main.Game;

public class IronDoor extends Entity
{
    public IronDoor(Game game)
    {
        super(game);

        this.type = Type.Key;
        this.down0 = setup("/resources/image/item/door_iron.png");
        this.solid = true;

        /*this.hitbox.x = 0;
        this.hitbox.y = 16;
        this.hitbox.width = 48;
        this.hitbox.height = 32;
        this.hitbox_default_x = this.hitbox.x;
        this.hitbox_default_y = this.hitbox.y;*/
    }
}