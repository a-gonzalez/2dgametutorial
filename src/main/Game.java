package unus.main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    // frame speed
    final int FPS = 60;

    Thread thread;
    Control control = new Control();

    public Game()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // better rendering performance for 2d games
        this.setFocusable(true);
        this.addKeyListener(control);
    }

    public void setup()
    {

    }

    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    int x = 100;
    int y = 20;
    int speed = 1;

    public void update()
    {
        if (control.direction != Direction.Idle)
        {
            switch (control.direction)
            {
                case Right :
                {
                    x += speed; break;
                }
                case Left :
                {
                    x -= speed; break;
                }
                case Up :
                {
                    y -= speed; break;
                }
                case Down :
                {
                    y += speed; break;
                }
            }
        }
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.RED);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

        g.dispose();
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        draw(g);
    }

    // implemenetations
    // runnable
    @Override
    public void run() // game loop
    {
        double interval = 1000000000 / FPS; // 1.66666 seconds
        double delta_time = 0;
        long previous_time = System.nanoTime();
        long current_time = 0;

        while (thread != null)
        {
            // 0 TIME: control how often events happen
            current_time = System.nanoTime();
            delta_time += (current_time - previous_time) / interval;
            previous_time = current_time;

            if (delta_time >= 1)
            {   // 1 UPDATE: player, npc positions / actions data
                update();
                // 2 DRAW: player, npc with UPDATE data
                repaint();

                delta_time--;
            }
        }
    }
}