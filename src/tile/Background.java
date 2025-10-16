package unus.tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;
import unus.main.Utility;

public class Background
{ // this class will manage the tiles that comprise the background
    public Tile[] tiles;
    Game game;
    int tile_size;

    public int map[][];

    public Background(Game game)
    {
        this.game = game;
        this.map = new int[game.WORLD_COLUMNS][game.WORLD_ROWS];

        tiles = new Tile[10];

        getImages();
        getMap("/resources/data/maps/003.dat");
    }

    public void setup(int index, String name, boolean solid)
    {
        Utility util = new Utility();

        try
        {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream(String.format("/resources/image/tile/%s.png", name)));
            tiles[index].image = util.scale(tiles[index].image, game.TILE_SIZE, game.TILE_SIZE);
            tiles[index].solid = solid;
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            util = null;
        }
    }

    public void draw(Graphics2D g2d)
    {
        int column = 0;
        int row = 0;

        while (column < game.WORLD_COLUMNS && row < game.WORLD_ROWS)
        {
            int world_x = column * game.TILE_SIZE;
            int world_y = row * game.TILE_SIZE;
            int screen_x = world_x - game.player.world_x + game.player.screen_x;
            int screen_y = world_y - game.player.world_y + game.player.screen_y;
            int tile = map[column][row];

            if (world_x + game.TILE_SIZE > game.player.world_x - game.player.screen_x &&
                world_x - game.TILE_SIZE < game.player.world_x + game.player.screen_x &&
                world_y + game.TILE_SIZE > game.player.world_y - game.player.screen_y &&
                world_y - game.TILE_SIZE < game.player.world_y + game.player.screen_y)
            { // let's only draw tile which are visible on screen
                //g2d.drawImage(tiles[tile].image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);
                g2d.drawImage(tiles[tile].image, screen_x, screen_y, null);
            }
            column++;

            if (column == game.WORLD_COLUMNS)
            {
                column = 0;
                row++;
            }
        }
    }

    private void getImages()
    {
        setup(0, "grass00", false);
        setup(1, "wall", true);
        setup(2, "water00", true);
        setup(3, "dirt", false);
        setup(4, "tree", true);
        setup(5, "road00", false);
    }

    private void getMap(String path)
    {
        try
        {
            InputStream stream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            int column = 0;
            int row = 0;

            while (column < game.WORLD_COLUMNS && row < game.WORLD_ROWS)
            {
                String line = reader.readLine();

                while (column < game.WORLD_COLUMNS)
                {
                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[column]);

                    map[column][row] = number;

                    column++;
                }
                if (column == game.WORLD_COLUMNS)
                {
                    column = 0;
                    row++;
                }
            }
            reader.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}