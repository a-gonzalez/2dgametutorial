package unus.entity;

import java.awt.Color;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

import unus.main.*;

public class Player extends Entity
{
    public final int screen_x;
    public final int screen_y;
    private Game game;
    private Control control;

    public Player(Control control, Game game)
    {
        super();

        this.control = control; // keyboard listener for movement
        this.game = game;

        this.screen_x = (game.SCREEN_WIDTH / 2) - (game.TILE_SIZE / 2);
        this.screen_y = (game.SCREEN_HEIGHT / 2) - (game.TILE_SIZE / 2);

        hitbox = new Rectangle(8, 16, 32, 32);

        setup();
        getImages();
    }

    public void setup()
    {
        this.world_x = game.TILE_SIZE * 23;
        this.world_y = game.TILE_SIZE * 21;
        this.speed = 3;
        this.direction = Direction.Down;
    }

    public void getImages()
    {
        try
        {
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/up1.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/down1.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/right1.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/image/player/left1.png"));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void update()
    {
        Direction direction = control.getDirection();

        if (direction != Direction.Idle)
        {
            this.direction = direction;

            /*switch (direction)
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
            }*/
            collision = false;

            // check solid tile collision
            game.bump.checkTile(this);

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
                if (sprite_number == 0)
                {
                    sprite_number = 1;
                }
                else if (sprite_number == 1)
                {
                    sprite_number = 0;
                }
                sprite_counter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d)
    {
        /*g2d.setColor(Color.RED);
        g2d.fillRect(x, y, tile_size, tile_size);*/
        BufferedImage image = null;

        switch (direction)
        {
            case Right :
            {
                if (sprite_number == 0)
                {
                    image = right0;
                }
                
                if (sprite_number == 1)
                {
                    image = right1;
                }
                break;
            }
            case Left :
            {
                if (sprite_number == 0)
                {
                    image = left0;
                }
                
                if (sprite_number == 1)
                {
                    image = left1;
                }
                break;
            }
            case Up :
            {
                if (sprite_number == 0)
                {
                    image = up0;
                }
                
                if (sprite_number == 1)
                {
                    image = up1;
                }
                break;
            }
            case Down :
            {
                if (sprite_number == 0)
                {
                    image = down0;
                }
                
                if (sprite_number == 1)
                {
                    image = down1;
                }
            }
        }
        g2d.drawImage(image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
        g2d.setColor(Color.GREEN);
        g2d.drawRect(screen_x + 8, screen_y + 16, hitbox.width, hitbox.height);
    }
}