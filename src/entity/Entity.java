package unus.entity;

import java.awt.image.BufferedImage;

import unus.main.Direction;

public class Entity
{
    public int world_x; 
    public int world_y;
    int width;
    int height;
    int speed;
    int sprite_counter = 0;
    int sprite_number = 1;

    public BufferedImage up0, up1, down0, down1, left0, left1, right0, right1;
    public Direction direction;
    
    public Entity()
    {
        initialize();
    }

    private void initialize()
    {

    }
}