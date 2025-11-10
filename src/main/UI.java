package unus.main;

import java.io.IOException;
//import java.io.InputStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
//import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import unus.entity.*;

public class UI
{
    Game game;
    Graphics2D g2d;
    BufferedImage heart_full, heart_half, heart_empty;
    Option option = Option.New;
    Font Papyrus_25P;
    //int message_counter = 0;
    public boolean game_complete = false;
    public boolean show_message = false;
    //public String message = "";
    public String dialoque = "";

    //Font pixel_zone, super_pixel;

    public UI(Game game)
    {
        this.game = game;
        
        initialize();
    }

    private void initialize()
    {
        /*try
        {// we can make use of any true-type font for the game
            InputStream stream = getClass().getResourceAsStream("/resources/font/pixel_zone.ttf");

            pixel_zone =  Font.createFont(Font.TRUETYPE_FONT, stream);

            stream = getClass().getResourceAsStream("/resources/font/super_pixel.ttf");

            super_pixel =  Font.createFont(Font.TRUETYPE_FONT, stream);
        }
        catch (FontFormatException exception)
        {
            exception.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }*/

        Papyrus_25P = new Font("Papyrus", Font.PLAIN, 25);

        Entity heart = new Heart(game);
        heart_full = heart.image;
        heart_half = heart.image1;
        heart_empty = heart.image2;
    }

    /*public void displayMessage(String message)
    {
        this.message = message;
        this.show_message = true;
    }*/

    public void draw(Graphics2D g2d)
    {
        this.g2d = g2d;

        g2d.setFont(Papyrus_25P);
        g2d.setColor(Color.white);

        if (game.state == State.Title)
        {
            drawTitleScreen();
        }

        if (game.state == State.Play)
        {
            drawPlayerLife();

            /*if (show_message)
            {
                int length = (int) g2d.getFontMetrics().getStringBounds(message, g2d).getWidth();
                int x = (game.SCREEN_WIDTH / 2) - (length / 2);
                int y = (game.SCREEN_HEIGHT / 2) - game.TILE_SIZE;

                g2d.drawString(message, x, y);

                ++message_counter;

                if (message_counter > 120)
                {
                    show_message = false;
                    message_counter = 0;
                }
            }*/
        }
        
        if (game.state == State.Pause)
        {
            drawPlayerLife();
            drawPauseScreen();
        }
        
        if (game.state == State.Dialoque)
        {
            drawPlayerLife();
            drawDialoqueScreen();
        }

        if (game.state == State.Status)
        {
            drawPlayerLife();
            drawStatusScreen();
        }
    }

    public void drawStatusScreen()
    {
        final int x = game.TILE_SIZE;
        final int y = game.TILE_SIZE;
        final int width = game.TILE_SIZE * 5;
        final int height = game.TILE_SIZE * 10;
        final int lineHeight = 35;

        drawSubWindow(x, y, width, height);

        g2d.setColor(Color.WHITE);

        int textX = x + 20;
        int textY = y + game.TILE_SIZE;

        g2d.drawString("Life", textX, textY);
        textY += lineHeight;
        g2d.drawString("Level", textX, textY);
        textY += lineHeight;
        g2d.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2d.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2d.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2d.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2d.drawString("Experience", textX, textY);
        textY += lineHeight;
        g2d.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2d.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2d.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2d.drawString("Shield", textX, textY);

        int rightX = (x + width) - 30;
        textY = y + game.TILE_SIZE;
        String value;

        value = String.valueOf(game.player.life + " / " + game.player.life_max);
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.level);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.strength);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.dexterity);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.attack);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.defense);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.experience);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.next_level_experience);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        value = String.valueOf(game.player.coin);
        textY += lineHeight;
        textX = getXToAlignTextRight(value, rightX);

        g2d.drawString(value, textX, textY);

        textY += lineHeight;

        g2d.drawImage(game.player.weapon.down0, rightX - game.TILE_SIZE, textY - 14, null);

        textY += game.TILE_SIZE;

        g2d.drawImage(game.player.shield.down0, rightX - game.TILE_SIZE, textY - 14, null);

        /*g2d.drawString(String.format("Life: %20d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Level: %16d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Strength: %10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Dexterity: %10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Attack: %-10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Defense: %-10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Experience: %10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Next Level: %5d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Coin: %-10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Weapon: %-10d", 0), textX, textY);
        textY += lineHeight;
        g2d.drawString(String.format("Shield: %-10d", 0), textX, textY);*/
    }

    public void drawPlayerLife()
    {
        int x = game.TILE_SIZE / 2;
        int y = game.TILE_SIZE / 2;
        int index = 0;

        while (index < game.player.life_max / 2)
        {// max life
            g2d.drawImage(heart_empty, x, y, null);
            index++;
            x += game.TILE_SIZE;
        }
        x = game.TILE_SIZE / 2;
        y = game.TILE_SIZE / 2;
        index = 0;

        while (index < game.player.life)
        {// current life
            g2d.drawImage(heart_half, x, y, null);
            index++;

            if (index < game.player.life)
            {
                g2d.drawImage(heart_full, x, y, null);
            }
            index++;
            x += game.TILE_SIZE;
        }
    }

    public void drawTitleScreen()
    {
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 60F));

        String text = "Blue Drifter Adventure";
        int x = getXToCenterText(text);
        int y = game.TILE_SIZE * 2;

        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x - 3, y - 3);
        g2d.setColor(Color.BLUE);
        g2d.drawString(text, x, y);

        x = game.SCREEN_WIDTH / 2 - (game.TILE_SIZE * 2) / 2;
        y += game.TILE_SIZE * 2;

        g2d.drawImage(game.player.down0, x, y, game.TILE_SIZE * 2, game.TILE_SIZE * 2, null);
        g2d.setFont(g2d.getFont().deriveFont(30F));

        text = "New";
        x = getXToCenterText(text);
        y += game.TILE_SIZE * 3.5;

        g2d.drawString(text, x, y);

        if (option == Option.New)
        {
            g2d.drawString(">", x - game.TILE_SIZE, y);
        }

        text = "Load";
        x = getXToCenterText(text);
        y += game.TILE_SIZE;

        g2d.drawString(text, x, y);

        if (option == Option.Load)
        {
            g2d.drawString(">", x - game.TILE_SIZE, y);
        }

        text = "Quit";
        x = getXToCenterText(text);
        y += game.TILE_SIZE;

        g2d.drawString(text, x, y);

        if (option == Option.Quit)
        {
            g2d.drawString(">", x - game.TILE_SIZE, y);
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
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 50F));

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

    private int getXToAlignTextRight(String text, int rightX)
    {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = rightX - length;

        return x;
    }
}