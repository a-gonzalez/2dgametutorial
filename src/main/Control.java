package unus.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener
{
    Direction direction = Direction.Idle;

    public Control()
    {

    }

    @Override
    public void keyTyped(KeyEvent event)
    { // we won't be using this method.
        //System.out.println(String.format("KeyType: %d", event.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        //System.out.println(String.format("KeyPressed: %d", event.getKeyCode()));

        switch (event.getKeyCode())
        {
            case 37 : // VK_LEFT
            {
                direction = Direction.Left; break;
            }
            case 38 : // VK_UP
            {
                direction = Direction.Up; break;
            }
            case 39 : // VK_RIGHT
            {
                direction = Direction.Right; break;
            }
            case 40 : // VK_DOWN
            {
                direction = Direction.Down; break;
            }
            default :
            {
                direction = Direction.Idle; break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        //System.out.println(String.format("KeyReleased: %d", event.getKeyCode()));

        switch (event.getKeyCode())
        {
            case 37 : // VK_LEFT
            {
                direction = Direction.Idle; break;
            }
            case 38 : // VK_UP
            {
                direction = Direction.Idle; break;
            }
            case 39 : // VK_RIGHT
            {
                direction = Direction.Idle; break;
            }
            case 40 : // VK_LEFT
            {
                direction = Direction.Idle; break;
            }
            default :
            {
                direction = Direction.Idle; break;
            }
        }
    }
}