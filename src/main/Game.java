package unus.main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Game extends JPanel
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
    final int MAX_WORLD_COLUMNS = 50;
    final int MAX_WORLD_ROWS = 50;
    final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COLUMNS;
    final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROWS;

    public Game()
    {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
    }
}