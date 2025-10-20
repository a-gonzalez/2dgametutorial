package unus.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.BasicStroke;

public class UI
{
    Game game;
    //BufferedImage key_image;
    Graphics2D g2d;
    Font Arial_20P;
    Font Arial_40P;
    Font Arial_40B;
    Font Arial_50P;
    Font ComicSans_25P;
    int message_counter = 0;
    public boolean game_complete = false;
    public boolean show_message = false;
    public String message = "";
    public String dialoque = "";

    public UI(Game game)
    {
        this.game = game;

        Arial_20P = new Font("Arial", Font.PLAIN, 20);
        Arial_40P = new Font("Arial", Font.PLAIN, 40);
        Arial_50P = new Font("Arial", Font.PLAIN, 50);
        Arial_40B = new Font("Arial", Font.BOLD, 40);

        this.ComicSans_25P = new Font("Comic Sans MS", Font.PLAIN, 25);
    }

    public void displayMessage(String message)
    {
        this.message = message;
        this.show_message = true;
    }

    public void draw(Graphics2D g2d)
    {
        this.g2d = g2d;

        g2d.setFont(Arial_20P);
        g2d.setColor(Color.white);

        if (game.state == State.Play)
        {

        }
        
        if (game.state == State.Pause)
        {
            drawPauseScreen();
        }
        
        if (game.state == State.Dialoque)
        {
            drawDialoqueScreen();
        }
    }

    public void drawDialoqueScreen()
    { // Window
        int x = game.TILE_SIZE * 2;
        int y = game.TILE_SIZE / 2;
        int width = game.SCREEN_WIDTH - (game.TILE_SIZE * 4);
        int height = game.TILE_SIZE * 4;

        drawSubWindow(x, y, width, height);

        x += game.TILE_SIZE;
        y += game.TILE_SIZE;

        g2d.setFont(ComicSans_25P);
        
        for (String line : dialoque.split("\n"))
        {
            g2d.drawString(line, x, y);

            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color color = new Color(0, 0, 0, 210);

        g2d.setColor(color);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPauseScreen()
    {
        g2d.setFont(Arial_50P);

        String text = "PAUSED";
        int x = getXToCenterText(text);
        int y = game.SCREEN_HEIGHT / 2;

        g2d.drawString(text, x, y);
    }

    private int getXToCenterText(String text)
    {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = game.SCREEN_WIDTH / 2 - length / 2;

        return x;
    }
}