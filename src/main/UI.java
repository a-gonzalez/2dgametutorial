package unus.main;

import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
import java.awt.Font;
//import java.awt.BasicStroke;

public class UI
{
    Game game;
    //BufferedImage key_image;
    Graphics2D g2d;
    Font Arial_20P;
    Font Arial_40P;
    Font Arial_40B;
    Font Arial_50P;
    Font ComicSans_30P;
    int message_counter = 0;
    public boolean game_complete = false;
    public boolean show_message = false;
    public String message = "";

    public UI(Game game)
    {
        this.game = game;

        Arial_20P = new Font("Arial", Font.PLAIN, 20);
        Arial_40P = new Font("Arial", Font.PLAIN, 40);
        Arial_50P = new Font("Arial", Font.PLAIN, 50);
        Arial_40B = new Font("Arial", Font.BOLD, 40);

        this.ComicSans_30P = new Font("Comic Sans", Font.PLAIN, 30);
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
    {

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