package unus.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import unus.main.Direction;

public class Entity
{
    public int world_x; 
    public int world_y;
    public int hitbox_default_x;
    public int hitbox_default_y;
    int speed;
    int sprite_counter = 0;
    int sprite_number = 1;

    public BufferedImage up0, up1, down0, down1, left0, left1, right0, right1;
    public Direction direction;
    public Rectangle hitbox;
    public boolean collision = false;

    public int getSpeed()
    {
        return speed;
    }
}