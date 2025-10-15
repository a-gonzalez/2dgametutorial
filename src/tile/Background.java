package unus.tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background
{ // this class will manage the tiles that comprise the background
    Tile[] tiles;
    int tile_size;
    int rows;
    int columns;

    int map[][];

    public Background(int tile_size, int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.tile_size = tile_size;
        this.map = new int[columns][rows];

        tiles = new Tile[10];

        getImages();
        getMap("/resources/data/maps/001.dat");
    }

    public void draw(Graphics2D g2d)
    {
        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < columns && row < rows)
        {
            int tile = map[column][row];

            g2d.drawImage(tiles[tile].image, x, y, tile_size, tile_size, null);

            column++;
            x += tile_size;

            if (column == columns)
            {
                column = 0;
                x = 0;
                row++;
                y += tile_size;
            }
        }
        /*g2d.drawImage(tiles[0].image, 0, 0, tile_size, tile_size, null);
        g2d.drawImage(tiles[1].image, 48, 0, tile_size, tile_size, null);
        g2d.drawImage(tiles[2].image, 96, 0, tile_size, tile_size, null);*/
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

            while (column < columns && row < rows)
            {
                String line = reader.readLine();

                while (column < columns)
                {
                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[column]);

                    map[column][row] = number;

                    column++;
                }
                if (column == columns)
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