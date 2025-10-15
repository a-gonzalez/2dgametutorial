package unus.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import unus.main.Game;

public class Item
{
    public BufferedImage image;
    public Rectangle hitbox;
    public Type type;
    public boolean solid;
    public int world_x;
    public int world_y;
    public int hitbox_default_x;
    public int hitbox_default_y;

    private Game game;

    public Item(Game game)
    {
        this.game = game;
        this.solid = false;
        this.hitbox = new Rectangle(0, 0, game.TILE_SIZE, game.TILE_SIZE);
        this.hitbox_default_x = 0;
        this.hitbox_default_y = 0;
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
                g2d.drawImage(image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
            }
    }
}