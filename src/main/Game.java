package unus.main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

//import java.lang.InterruptedException;

public class Game extends JPanel implements Runnable
{
    // screen (window) settings
    final int TILE_SIZE_ORIGINAL = 16;
    final int SCALE = 3;
    final int TILE_SIZE = TILE_SIZE_ORIGINAL * SCALE;
    final int SCREEN_COLUMNS = 16;
    final int SCREEN_ROWS = 12;
    final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLUMNS;
    final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;

    // world settings
    final int WORLD_COLUMNS = 50;
    final int WORLD_ROWS = 50;
    final int WORLD_WIDTH = TILE_SIZE * WORLD_COLUMNS;
    final int WORLD_HEIGHT = TILE_SIZE * WORLD_ROWS;

    Thread thread;

    public Game()
    {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true); // better rendering performance for 2d games
        setFocusable(true);
    }

    public void setup()
    {

    }

    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    int x = 10;
    int y = 20;
    int size = 50;

    public void update()
    {
        x += 2;
        y += 2;
    }

    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, size, size);
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        draw(graphics);
    }

    // implemenetations
    // runnable
    @Override
    public void run()
    {
        while (thread != null)
        {
            //System.out.println("Runnings...");
            // 1 UPDATE: player, npc positions / actions data
            update();

            // 2 DRAW: player, npc with UPDATE data
            repaint();

            /*try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }*/
        }
    }
}