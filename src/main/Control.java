package unus.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener
{
    Direction direction;
    private Game game;

    public boolean enter_pressed = false;
    public boolean space_pressed = false;

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
        if (game.state == State.Title)
        {
            if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == event.VK_W)
            {
                if (game.ui.option == Option.New)
                {
                    game.ui.option = Option.Quit;
                }
                else if (game.ui.option == Option.Quit)
                {
                    game.ui.option = Option.Load;
                }
                else if (game.ui.option == Option.Load)
                {
                    game.ui.option = Option.New;
                }
            }
            else if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == event.VK_S)
            {
                if (game.ui.option == Option.New)
                {
                    game.ui.option = Option.Load;
                }
                else if (game.ui.option == Option.Load)
                {
                    game.ui.option = Option.Quit;
                }
                else if (game.ui.option == Option.Quit)
                {
                    game.ui.option = Option.New;
                }
            }
            else if (event.getKeyCode() == KeyEvent.VK_ENTER)
            {
                switch (game.ui.option)
                {
                    case New :
                    {
                        game.state = State.Play;
                        //game.playMusic(0);
                        break;
                    }
                    case Load :
                    {
                        // this will be implemented after we implement saving the game
                        break;
                    }
                    case Quit :
                    {
                        System.exit(0);
                    }
                }
            }
        }
        else if (game.state == State.Play)
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
                case KeyEvent.VK_SPACE :
                {
                    space_pressed = true;
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
                case KeyEvent.VK_SPACE :
                {
                    space_pressed = false; break;
                }
            }
        }
    }
}