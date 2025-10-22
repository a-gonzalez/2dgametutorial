package unus.main;

import java.awt.Rectangle;

public class EventRectangle extends Rectangle
{
    int hitbox_default_x;
    int hitbox_default_y;
    boolean complete = false;

    public EventRectangle(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }
}