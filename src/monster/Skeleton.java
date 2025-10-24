package unus.monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import unus.entity.*;
import unus.main.*;

public class Skeleton extends Entity
{
    public Skeleton(Game game)
    {
       super(game);

       initialize();
    }

    private void initialize()
    {
        type = Type.Monster;
        speed = 1;
        life_max = 8;
        life = life_max;
        speed = 2;
        direction = Direction.Down;

        hitbox.x = 10;
        hitbox.y = 16;
        hitbox.width = 30;
        hitbox.height = 32;
        hitbox_default_x = this.hitbox.x;
        hitbox_default_y = this.hitbox.y;

        setImages();
    }

    public void speak()
    {
        super.speak();
    }

    public void setAction()
    {
        action_counter++;

        if (action_counter == 120) // 2 seconds (2 frames)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

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

    private void setImages()
    {
        getWalkingImages();
        getAttackImages();
    }

    private void getWalkingImages()
    {
        down0 = setup("/resources/image/monster/skeleton/down0.png");
        down1 = setup("/resources/image/monster/skeleton/down1.png");
        up0 = setup("/resources/image/monster/skeleton/up0.png");
        up1 = setup("/resources/image/monster/skeleton/up1.png");
        left0 = setup("/resources/image/monster/skeleton/left0.png");
        left1 = setup("/resources/image/monster/skeleton/left1.png");
        right0 = setup("/resources/image/monster/skeleton/right0.png");
        right1 = setup("/resources/image/monster/skeleton/right1.png");
    }

    private void getAttackImages()
    {
        int size = 48;

        attack_down0 = setup("/resources/image/monster/skeleton/attack_down0.png", size, size * 2);
        attack_down1 = setup("/resources/image/monster/skeleton/attack_down1.png", size, size * 2);
        attack_up0 = setup("/resources/image/monster/skeleton/attack_up0.png", size, size * 2);
        attack_up1 = setup("/resources/image/monster/skeleton/attack_up1.png", size, size * 2);
        attack_left0 = setup("/resources/image/monster/skeleton/attack_left0.png", size * 2, size);
        attack_left1 = setup("/resources/image/monster/skeleton/attack_left1.png", size * 2, size);
        attack_right0 = setup("/resources/image/monster/skeleton/attack_right0.png", size * 2, size);
        attack_right1 = setup("/resources/image/monster/skeleton/attack_right1.png", size * 2, size);
    }
}