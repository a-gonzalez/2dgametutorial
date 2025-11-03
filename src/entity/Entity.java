package unus.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import unus.main.*;

public abstract class Entity
{
    public BufferedImage up0, up1, down0, down1, left0, left1, right0, right1;
    public BufferedImage attack_up0, attack_up1, attack_down0, attack_down1, attack_left0, attack_left1, attack_right0, attack_right1;
    public BufferedImage image, image1, image2;
    public Rectangle hitbox;
    public Rectangle attack_hitbox = new Rectangle(0, 0, 0, 0);
    public int hitbox_default_x, hitbox_default_y;
    String[] dialoques = new String[10];

    // state
    public int world_x, world_y;
    public Direction direction;
    int sprite_number = 0;
    public boolean solid = false;
    public boolean invincible = false;
    int dialoque_index = 0;
    public boolean collision = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean health_bar_show = false;

    // counters
    public int action_counter = 0;
    int sprite_counter = 0;
    int invincible_counter = 0;
    int dying_counter = 0;
    int health_bar_counter = 0;

    // attributes
    public Type type;
    public int speed;
    public int life_max;
    public int life;

    Game game;

    public Entity(Game game)
    {
        this.game = game;
        this.hitbox = new Rectangle(0, 0, 48, 48);
        this.type = Type.None;
        this.direction = Direction.Down;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setAction()
    {
    }

    public void damageReaction()
    {
        action_counter = 0;
        direction = game.player.direction;
    }

    public void speak()
    {
        if (dialoques[dialoque_index] == null)
        {
            dialoque_index = 0;
        }
        game.ui.dialoque = dialoques[dialoque_index];

        dialoque_index++;

        switch (game.player.direction)
        {
            case Up :
            {
                direction = Direction.Down; break;
            }
            case Down :
            {
                direction = Direction.Up; break;
            }
            case Right :
            {
                direction = Direction.Left; break;
            }
            case Left :
            {
                direction = Direction.Right; break;
            }
        }
    }

    public void dyingAnimation(Graphics2D g2d)
    {
        ++dying_counter;

        int increment = 5;

        if (dying_counter <= increment)
        {
            changeAlpha(g2d, 0f);
        }

        if (dying_counter > increment && dying_counter <= increment * 2)
        {
            changeAlpha(g2d, 1f);
        }

        if (dying_counter > increment * 2 && dying_counter <= increment * 3)
        {
            changeAlpha(g2d, 0f);
        }

        if (dying_counter > increment * 3 && dying_counter <= increment * 4)
        {
            changeAlpha(g2d, 1f);
        }

        if (dying_counter > increment * 4 && dying_counter <= increment * 5)
        {
            changeAlpha(g2d, 0f);
        }

        if (dying_counter > increment * 5 && dying_counter <= increment * 6)
        {
            changeAlpha(g2d, 1f);
        }

        if (dying_counter > increment * 6 && dying_counter <= increment * 7)
        {
            changeAlpha(g2d, 0f);
        }

        if (dying_counter > increment * 7 && dying_counter <= increment * 8)
        {
            changeAlpha(g2d, 1f);
        
            if (dying_counter >= increment * 8)
            {
                dying = false;
                alive = false;
            }
        }
    }

    public void changeAlpha(Graphics2D g2d, float alpha)
    {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public void update()
    {
        setAction();

        collision = false;

        game.bump.checkTile(this);
        game.bump.checkItem(this, false);
        game.bump.checkEntity(this, game.npc);
        game.bump.checkEntity(this, game.monsters);
        boolean contact = game.bump.checkPlayer(this);

        if (contact == true && this.type == Type.Monster)
        { // if a monster hits player and player is not invincible give damage
            if (game.player.invincible == false)
            {
                game.player.life--;
                game.player.invincible = true;
            }
        }

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

        if (invincible == true)
        {
            ++invincible_counter;

            if (invincible_counter > 40)
            {
                invincible = false;
                invincible_counter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d)
    {
        int screen_x = world_x - game.player.world_x + game.player.screen_x;
        int screen_y = world_y - game.player.world_y + game.player.screen_y;

        if (world_x + game.TILE_SIZE > game.player.world_x - game.player.screen_x &&
            world_x - game.TILE_SIZE < game.player.world_x + game.player.screen_x &&
            world_y + game.TILE_SIZE > game.player.world_y - game.player.screen_y &&
            world_y - game.TILE_SIZE < game.player.world_y + game.player.screen_y)
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


                if (type == Type.Monster && health_bar_show == true)
                {// health bar
                    double bar = (double) game.TILE_SIZE / life_max;
                    double health = bar * life;

                    g2d.setColor(new Color(35, 35, 35));
                    g2d.fillRect(screen_x - 1, screen_y - 16, game.TILE_SIZE + 2, 12);
                    g2d.setColor(new Color(255, 0, 30));
                    g2d.fillRect(screen_x, screen_y - 15, (int) health, 10);

                    ++health_bar_counter;

                    if (health_bar_counter > 400)
                    {
                        health_bar_counter = 0;
                        health_bar_show = false;
                    }
                }

                if (invincible == true)
                {
                    health_bar_show = true;
                    health_bar_counter = 0;

                    changeAlpha(g2d, 0.4f);
                }

                if (dying == true)
                {
                    dyingAnimation(g2d);
                }
                g2d.drawImage(image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
                changeAlpha(g2d, 1f);

                if (this.type == Type.Monster)
                {
                    g2d.setColor(Color.RED);
                }
                else
                {
                    g2d.setColor(Color.GREEN);
                }
                g2d.drawRect(screen_x + hitbox.x, screen_y + hitbox.y, hitbox.width, hitbox.height);
            }
    }

    public BufferedImage setup(String path)
    {
        return setup(path, game.TILE_SIZE, game.TILE_SIZE);
    }

    public BufferedImage setup(String path, int width, int height)
    {
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = new Utility().scale(image, width, height);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        return image;
    }
}