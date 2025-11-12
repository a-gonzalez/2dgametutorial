package unus.entity;

import unus.main.Game;

public class Boot extends Entity
{
    public Boot(Game game)
    {
        super(game);

        this.type = Type.Boot;
        this.down0 = setup("/resources/image/item/boot.png");
        description = String.format("[%s]\nSpeed boost.", getClass().getSimpleName());
    }
}