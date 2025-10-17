package unus.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener
{
    private Direction direction;
    private Game game;

    public Control(Game game)
    {
        this.direction = Direction.Idle;
        this.game = game;
    }

    public Direction getDirection()
    {
        return direction;
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
            case 65 : // VK_A
            {
                direction = Direction.Left; break;
            }
            case 38 : // VK_UP
            case 87 : // VK_W
            {
                direction = Direction.Up; break;
            }
            case 39 : // VK_RIGHT
            case 68 : // VK_D
            {
                direction = Direction.Right; break;
            }
            case 40 : // VK_DOWN
            case 83 : // VK_S
            {
                direction = Direction.Down; break;
            }
            case 80 : // VK_P
            {
                if (game.state == State.Play)
                {
                    game.state = State.Pause;
                }
                else
                {
                    game.state = State.Play;
                }
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
            case 65 : // VK_A
            {
                direction = Direction.Idle; break;
            }
            case 38 : // VK_UP
            case 87 : // VK_W
            {
                direction = Direction.Idle; break;
            }
            case 39 : // VK_RIGHT
            case 68 : // VK_D
            {
                direction = Direction.Idle; break;
            }
            case 40 : // VK_DOWN
            case 83 : // VK_S
            {
                direction = Direction.Idle; break;
            }
        }
    }
}