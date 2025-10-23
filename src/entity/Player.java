package unus.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.AlphaComposite;

import unus.main.*;

public class Player extends Entity
{
    public final int screen_x;
    public final int screen_y;
    //public int keys = 0;
    private Control control;

    private int idle_counter = 0;
    //private int pixel_counter = 0;
    private int dialoque_counter = 60;

    public Player(Game game, Control control)
    {
        super(game);

        this.control = control; // keyboard listener for movement
        this.screen_x = (game.SCREEN_WIDTH / 2) - (game.TILE_SIZE / 2);
        this.screen_y = (game.SCREEN_HEIGHT / 2) - (game.TILE_SIZE / 2);

        initialize();
    }

    public void initialize()
    {
        hitbox = new Rectangle(8, 16, 32, 32);
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
        world_x = game.TILE_SIZE * 23;
        world_y = game.TILE_SIZE * 21;
        speed = 4;
        direction = Direction.Down;
        life_max = 6;
        life = life_max;

        setImages();
    }

    private void setImages()
    {
        up0 = setup("/resources/image/player/up0.png");
        up1 = setup("/resources/image/player/up1.png");
        down0 = setup("/resources/image/player/down0.png");
        down1 = setup("/resources/image/player/down1.png");
        right0 = setup("/resources/image/player/right0.png");
        right1 = setup("/resources/image/player/right1.png");
        left0 = setup("/resources/image/player/left0.png");
        left1 = setup("/resources/image/player/left1.png");
    }

    public void grabItem(int index)
    {
        if (index != 999)
        {
        }
    }

    public void contactNPC(int index)
    {
        if (index != 999)
        {
            if (game.control.enter_pressed == true)
            {
                game.state = State.Dialoque;
                game.npc[index].speak();
            }
        }
    }

    public void contactMonster(int index)
    {
        if (index != 999)
        {
            if (invincible == false)
            {
                --life;
                invincible = true;
            }
        }
    }

    public void update()
    {
        Direction direction = control.getDirection();

        if (direction != Direction.Idle)
        {
            this.direction = direction;

            collision = false;

            // check solid tile collision
            game.bump.checkTile(this);

            // check item collision
            int index = game.bump.checkItem(this, true);
            grabItem(index);

            // check NPC collision
            index = game.bump.checkEntity(this, game.npc);

            contactNPC(index);

            index = game.bump.checkEntity(this, game.monsters);

            contactMonster(index);

            // check events
            game.event.check();

            game.control.enter_pressed = false;

            if (collision == false)
            { // if no collision, player can move
                switch (this.direction)
                {
                    case Right :
                    {
                        world_x += speed; break;
                    }
                    case Left :
                    {
                        world_x -= speed; break;
                    }
                    case Up :
                    {
                        world_y -= speed; break;
                    }
                    case Down :
                    {
                        world_y += speed; break;
                    }
                }
            }
            sprite_counter++;

            if (sprite_counter > 14)
            {
                sprite_number = (sprite_number == 0) ? 1 : 0;
                sprite_counter = 0;
            }
            /*pixel_counter += speed;

            if (pixel_counter == 46)
            {
                direction = Direction.Idle;

                pixel_counter = 0;
            }*/
        }
        else
        {
            idle_counter++;

            if (idle_counter > 30)
            {
                sprite_number = 0;
                idle_counter = 0;
            }
        }

        if (invincible == true)
        {
            ++invincible_counter;

            if (invincible_counter > 60)
            {
                invincible = false;
                invincible_counter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d)
    {
        BufferedImage image = null;

        switch (direction)
        {
            case Right :
            {
                image = (sprite_number == 0) ? right0 : right1; break;
            }
            case Left :
            {
                image = (sprite_number == 0) ? left0 : left1; break;
            }
            case Up :
            {
                image = (sprite_number == 0) ? up0 : up1; break;
            }
            case Down :
            {
                image = (sprite_number == 0) ? down0 : down1; break;
            }
        }

        if (invincible == true)
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2d.drawImage(image, screen_x, screen_y, null);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 01f));

        g2d.setColor(Color.GREEN);
        g2d.drawRect(screen_x + hitbox.x, screen_y + hitbox.y, hitbox.width, hitbox.height);
    }
}