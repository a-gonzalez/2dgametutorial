package unus.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.BasicStroke;
import java.text.DecimalFormat;

import unus.item.*;

public class UI
{
    Game game;
    BufferedImage key_image;
    Graphics2D g2d;
    Font Arial_20P;
    Font Arial_40B;
    //Font Arial_80B;
    int message_counter = 0;
    public boolean game_complete = false;
    public boolean show_message = false;
    public String message = "";

    double play_time;
   // DecimalFormat dformat;

    public UI(Game game)
    {
        this.game = game;
        this.Arial_20P = new Font("Arial", Font.PLAIN, 20);
        this.Arial_40B = new Font("Arial", Font.BOLD, 40);
        //this.Arial_80 = new Font("Arial", Font.BOLD, 80);

        //dformat = new DecimalFormat("#0.00");

        Item key = new Key(game);
        key_image = key.image;
    }

    public void displayMessage(String message)
    {
        this.message = message;
        this.show_message = true;
    }

    public void draw(Graphics2D g2d)
    {
        if (game_complete)
        {
            String text;
            int length, x, y;

            g2d.setColor(Color.WHITE);
            g2d.setFont(Arial_20P); // set font-size before measure

            text = "You found the treasure!";
            length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (game.SCREEN_WIDTH / 2) - (length / 2);
            y = (game.SCREEN_HEIGHT / 2) - game.TILE_SIZE * 2 + 25;

            g2d.drawString(text, x, y);

            text = "Your time was: %.2f!";
            length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (game.SCREEN_WIDTH / 2) - (length / 2);
            y = (game.SCREEN_HEIGHT / 2) + game.TILE_SIZE * 3 + 20;

            g2d.drawString(String.format(text, play_time), x, y);

            g2d.setFont(Arial_40B);

            text = "Congratulations";
            length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = (game.SCREEN_WIDTH / 2) - (length / 2);
            y = (game.SCREEN_HEIGHT / 2) + game.TILE_SIZE * 2;

            g2d.drawString(text, x, y);

            game.thread = null;
        }
        else
        {
            g2d.setColor(Color.YELLOW);
            g2d.setFont(Arial_20P);
            g2d.drawImage(key_image, 10, 10, game.TILE_SIZE, game.TILE_SIZE, null);
            g2d.drawString(" " + game.player.keys, 40, 55);

            play_time += (double) 1 / 60;

            g2d.drawString(String.format("Time: %.2f", play_time), game.TILE_SIZE * 12, 55);
            //g2d.drawString("Time: " + dformat.format(play_time), game.TILE_SIZE * 12, 55);

            // messages
            if (show_message)
            {
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(30F));
                g2d.drawString(message, 10, 100);

                message_counter++;

                if (message_counter >100)
                {
                    show_message = false;
                    message_counter = 0;
                }
            }
        }
    }
}