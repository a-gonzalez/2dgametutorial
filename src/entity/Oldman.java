package unus.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import unus.main.*;

public class Oldman extends Entity
{
    public Oldman(Game game)
    {
       super(game);

       initialize();
    }

    private void initialize()
    {
        this.speed = 1;
        this.direction = Direction.Down;

        this.hitbox.x = 0;
        this.hitbox.y = 16;
        this.hitbox.width = 46;
        this.hitbox.height = 32;
        this.hitbox_default_x = this.hitbox.x;
        this.hitbox_default_y = this.hitbox.y;

        setImages();
        setDialoques();
    }

    public void speak()
    {
        super.speak();
    }

    public void setAction()
    {// simplest AI of all time
        action_counter++;

        if (action_counter == 120) // 2 seconds
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1 - 100 instead of 0 - 99

            if (i <= 25)
            { // 25 percent of the time to up
                direction = Direction.Up;
            }

            if (i > 25 && i <= 50)
            { // 25 percent of the time to down
                direction = Direction.Down;
            }

            if (i > 50 && i <= 75)
            { // 25 percent of the time to left
                direction = Direction.Left;
            }

            if (i > 75 && i <= 100)
            { // 25 percent of the time to right
                direction = Direction.Right;
            }
            action_counter = 0;
        }
    }

    public void setDialoques()
    {
        dialoques[0] = "Greetings, young sir.";
        dialoques[1] = "Are you lost?";
        dialoques[2] = "Watch out for monsters!";
        dialoques[3] = "Be careful exploring the island.";
        dialoques[4] = "Beware of the pits around the island.";
        dialoques[5] = "Some of the water on the island heals damage.";
        dialoques[6] = "In another life, I would have really liked\njust doing laundry and taxes with you.\nA bunch of other stuff to say too. Maybe\neven a trip to the beach to get some sun.";
    }

    private void setImages()
    {
        down0 = setup("/resources/image/npc/oldman/down0.png");
        down1 = setup("/resources/image/npc/oldman/down1.png");
        up0 = setup("/resources/image/npc/oldman/up0.png");
        up1 = setup("/resources/image/npc/oldman/up1.png");
        left0 = setup("/resources/image/npc/oldman/left0.png");
        left1 = setup("/resources/image/npc/oldman/left1.png");
        right0 = setup("/resources/image/npc/oldman/right0.png");
        right1 = setup("/resources/image/npc/oldman/right1.png");
    }
}