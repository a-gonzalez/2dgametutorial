package unus.tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import unus.main.Game;

public class Background
{ // this class will manage the tiles that comprise the background
    Tile[] tiles;
    Game game;
    int tile_size;

    int map[][];

    public Background(Game game)
    {
        this.game = game;
        this.map = new int[game.WORLD_COLUMNS][game.WORLD_ROWS];

        tiles = new Tile[10];

        getImages();
        getMap("/resources/data/maps/003.dat");
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

            g2d.drawImage(tiles[tile].image, screen_x, screen_y, game.TILE_SIZE, game.TILE_SIZE, null);

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
        try
        {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/grass00.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/wall.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/water00.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/dirt.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/tree.png"));
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/image/tile/road00.png"));

        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
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