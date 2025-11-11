package unus.monster;

import java.util.Random;

import unus.entity.*;
import unus.main.*;

public class Bat extends Entity
{
    public Bat(Game game)
    {
        super(game);

        initialize();
    }

    private void initialize()
    {
        type = Type.Monster;
        speed = 1;
        life_max = 2;
        life = life_max;
        attack = 3;
        defense = 0;
        experience = 1;

        hitbox.x = 4;
        hitbox.y = 14;
        hitbox.width = 42;
        hitbox.height = 20;
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;

        setImages();
    }

    private void setImages()
    {
        up0 = setup("/resources/image/monster/bat/down0.png");
        up1 = setup("/resources/image/monster/bat/down1.png");
        down0 = setup("/resources/image/monster/bat/down0.png");
        down1 = setup("/resources/image/monster/bat/down1.png");
        left0 = setup("/resources/image/monster/bat/down0.png");
        left1 = setup("/resources/image/monster/bat/down1.png");
        right0 = setup("/resources/image/monster/bat/down0.png");
        right1 = setup("/resources/image/monster/bat/down1.png");
    }

    public void setAction()
    {
        action_counter++;

        if (action_counter == 120) // 2 seconds
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1 - 100 instead of 0 - 99

            if (i <= 25)
            {
                direction = Direction.Up;
            }

            if (i > 25 && i <= 50)
            {
                direction = Direction.Down;
            }

            if (i > 50 && i <= 75)
            {
                direction = Direction.Left;
            }

            if (i > 75 && i <= 100)
            {
                direction = Direction.Right;
            }
            action_counter = 0;
        }
    }
}