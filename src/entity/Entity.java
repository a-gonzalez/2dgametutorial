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

    // counters
    public int action_counter = 0;
    int sprite_counter = 0;
    int invincible_counter = 0;

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

                if (invincible == true)
                {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                }
                g2d.drawImage(image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

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