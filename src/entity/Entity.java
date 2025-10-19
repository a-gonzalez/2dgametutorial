package unus.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import unus.main.*;

public class Entity
{
    public int world_x; 
    public int world_y;
    public int hitbox_default_x;
    public int hitbox_default_y;
    public int action_counter = 0;

    public BufferedImage up0, up1, down0, down1, left0, left1, right0, right1;
    public Direction direction;
    public Rectangle hitbox;
    public boolean collision = false;

    int speed;
    int sprite_counter = 0;
    int sprite_number = 0;

    Game game;

    public Entity(Game game)
    {
        this.game = game;
        this.hitbox = new Rectangle(0, 0, 48, 48);
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setAction()
    {
    }

    public void update()
    {
        setAction();

        collision = false;

        game.bump.checkTile(this);
        game.bump.checkItem(this, false);
        game.bump.checkPlayer(this);

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
                g2d.drawImage(image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
            }
    }

    public BufferedImage setup(String path)
    {
        Utility util = new Utility();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = util.scale(image, game.TILE_SIZE, game.TILE_SIZE);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        return image;
    }
}