package unus.main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import java.lang.InterruptedException;

import unus.entity.*;
import unus.tile.Background;

public class Game extends JPanel implements Runnable
{
    // screen (window) settings
    final int TILE_SIZE_ORIGINAL = 16;
    final int SCALE = 3;
    public final int TILE_SIZE = TILE_SIZE_ORIGINAL * SCALE;
    final int SCREEN_COLUMNS = 16;
    final int SCREEN_ROWS = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLUMNS;
    public final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;

    // world settings
    public final int WORLD_COLUMNS = 50;
    public final int WORLD_ROWS = 50;
    //final int WORLD_WIDTH = TILE_SIZE * WORLD_COLUMNS;
    //final int WORLD_HEIGHT = TILE_SIZE * WORLD_ROWS;

    // frame speed
    final int FPS = 60;

    Thread thread;
    Sound music = new Sound();
    Sound se = new Sound();
    Background background = new Background(this);
    public Control control = new Control(this);
    public Event event = new Event(this);
    public UI ui = new UI(this);
    public Assets assets = new Assets(this);
    public Bump bump = new Bump(this);
    public State state;
    public Player player = new Player(this, control);
    public Entity[] items = new Entity[10];
    public Entity[] npc = new Entity[10];

    ArrayList<Entity> list = new ArrayList<Entity>();

    public Game()
    {
        initialize();
    }

    public void initialize()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // better rendering performance for 2d games
        this.setFocusable(true);
        this.addKeyListener(control);
    }

    public void setup()
    {
        assets.setItems();
        assets.setNPCs();

        //playMusic(0);

        state = State.Title;
    }

    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    public void playMusic(int index)
    {
        music.setSound(index);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int index)
    {
        se.setSound(index);
        se.play();
    }

    public void update()
    {
        if (state == State.Play)
        {
            player.update();

            for (int index = 0; index < npc.length; index++)
            {
                if (npc[index] != null)
                {
                    npc[index].update();
                }
            }
        }
    }

    public void draw(Graphics2D g)
    {
        // DEBUG = how long does it take to draw images?
        /*long start = System.nanoTime();
        long end = 0;*/
        if (state == State.Title)
        {
            ui.draw(g);
        }
        else
        {
            background.draw(g);

            list.add(player);

            for (int index = 0; index < npc.length; index++)
            {
                if (npc[index] != null)
                {
                    list.add(npc[index]);
                }
            }

            for (int index = 0; index < items.length; index++)
            {
                if (items[index] != null)
                {
                    list.add(items[index]);

                    //System.out.println(items[index].getClass().getName());
                }
            }

            Collections.sort(list, new Comparator<Entity>()
            {
                @Override
                public int compare(Entity a, Entity b)
                {
                    int result = Integer.compare(a.world_y, b.world_y);

                    return result;
                }
            });

            for (int index = 0; index < list.size(); index++)
            {
                list.get(index).draw(g);
            }
            list.clear();

            ui.draw(g);
        }
        /*end = System.nanoTime();
        long elapsed = end - start;

        g.drawString(String.format("Draw Time: %d", elapsed), 100, 100);
        System.out.println(String.format("Draw Time: %d", elapsed));*/

        g.dispose();
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        draw(g);

        g.dispose();
    }

    // implemenetations
    // runnable
    @Override
    public void run() // game loop - delta-time or accumulator method
    { // 1,000,000,000 nanoseconds equals 1 second
        double interval = 1000000000 / FPS; // 0.0166666 seconds
        double delta_time = 0;
        long previous_time = System.nanoTime();
        long current_time = 0;

        // testing
        /*long timer = 0;
        int count = 0;*/

        while (thread != null)
        {
            // 0 TIME: control how often events happen
            current_time = System.nanoTime();
            delta_time += (current_time - previous_time) / interval;

            // testing
            //timer += (current_time - previous_time);

            previous_time = current_time;

            if (delta_time >= 1)
            {   // 1 UPDATE: player, npc positions / actions data
                update();
                // 2 DRAW: player, npc with UPDATE data
                repaint();

                delta_time--;

                //count++; // testing
            }

            /*if (timer >= 1000000000)
            { // testing
                System.out.println(String.format("FPS: %d", count));

                count = 0;
                timer = 0;
            }*/
        }
    }
    /*@Override
    public void run() // game loop - sleep method
    {
        double draw_interval = 1000000000 / FPS;
        double next_draw_time = System.nanoTime() + draw_interval;

        while (thread != null)
        {
            // 0 TIME: control how often events happen
            

            // 1 UPDATE: player, npc positions / actions data
                update();
                // 2 DRAW: player, npc with UPDATE data
                repaint();

            try
            {
                double remaining_time = (next_draw_time - System.nanoTime()) / 1000000;

                if (remaining_time < 0)
                {
                    remaining_time = 0;
                }
                Thread.sleep((long) remaining_time);

                next_draw_time += draw_interval;
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }
    }*/
}