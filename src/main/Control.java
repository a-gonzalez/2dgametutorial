package unus.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener
{
    private Direction direction;
    private Game game;

    public boolean enter_pressed = false;

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
        if (game.state == State.Play)
        {
            switch (event.getKeyCode())
            {
                case KeyEvent.VK_LEFT :
                case KeyEvent.VK_A :
                {
                    direction = Direction.Left; break;
                }
                case KeyEvent.VK_UP :
                case KeyEvent.VK_W :
                {
                    direction = Direction.Up; break;
                }
                case KeyEvent.VK_RIGHT :
                case KeyEvent.VK_D :
                {
                    direction = Direction.Right; break;
                }
                case KeyEvent.VK_DOWN :
                case KeyEvent.VK_S :
                {
                    direction = Direction.Down; break;
                }
                case KeyEvent.VK_P :
                {
                    game.state = State.Pause; break;
                }
                case KeyEvent.VK_ENTER :
                {
                    enter_pressed = true;
                }
            }
        }
        else if (game.state == State.Pause)
        {
            if (event.getKeyCode() == KeyEvent.VK_P)
            {
                game.state = State.Play;
            }
        }
        else if (game.state == State.Dialoque)
        {
            if (event.getKeyCode() == KeyEvent.VK_ENTER)
            {
                game.state = State.Play;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        //System.out.println(String.format("KeyReleased: %d", event.getKeyCode()));
        if (game.state == State.Play)
        {
            switch (event.getKeyCode())
            {
                case KeyEvent.VK_LEFT :
                case KeyEvent.VK_A :
                {
                    direction = Direction.Idle; break;
                }
                case KeyEvent.VK_UP :
                case KeyEvent.VK_W :
                {
                    direction = Direction.Idle; break;
                }
                case KeyEvent.VK_RIGHT :
                case KeyEvent.VK_D :
                {
                    direction = Direction.Idle; break;
                }
                case KeyEvent.VK_DOWN :
                case KeyEvent.VK_S :
                {
                    direction = Direction.Idle; break;
                }
            }
        }
    }
}