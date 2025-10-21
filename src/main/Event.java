package unus.main;

import java.awt.Rectangle;

public class Event
{
    int hitbox_default_x;
    int hitbox_default_y;

    Rectangle hitbox;
    Game game;

    public Event(Game game)
    {
        this.game = game;

        initialize();
    }

    private void initialize()
    {
        hitbox = new Rectangle(23, 23, 2, 2);
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
    }

    public void damagePit(State state)
    {
        /*game.state = state;
        game.ui.dialoque = "Danger!";*/
        --game.player.life;

        game.ui.displayMessage("Danger! There is a pit here.");
        System.out.println("Hit!");
    }

    public void healingPool(State state)
    {
        if (game.control.enter_pressed)
        {
            /*game.state = state;
            game.ui.dialoque = "You drank healing water. Your life has\nbeen restored.";*/
            if (game.player.life < game.player.life_max)
            {
                game.player.life = game.player.life_max;

                game.ui.displayMessage("Your life has been restored.");
            }
            else
            {
                game.ui.displayMessage("No healing required.");
            }
        }
    }

    public void teleport(State state)
    {
        /*game.state = state;
        game.ui.dialoque = "Teleport!";*/
        game.player.world_x = game.TILE_SIZE * 37;
        game.player.world_y = game.TILE_SIZE * 10;

        game.ui.displayMessage("Teleport.");
    }

    public void check()
    {//TODO: try interactin with "hits" in the same way NPCs do (speak method)
        if (hit(27, 16, Direction.Right))
        {
            //damagePit(State.Dialoque);
            teleport(State.Dialoque);
        }

        if (hit(23, 12, Direction.Up))
        {
            healingPool(State.Dialoque);
        }

        if (hit(10, 40, Direction.Up))
        {
            teleport(State.Dialoque);
        }
    }

    public boolean hit(int column, int row, Direction direction)
    {
        boolean result = false;

        game.player.hitbox.x = game.player.world_x + game.player.hitbox.x;
        game.player.hitbox.y = game.player.world_y + game.player.hitbox.y;
        hitbox.x = column * game.TILE_SIZE + hitbox.x;
        hitbox.y = row * game.TILE_SIZE + hitbox.y;

        if (game.player.hitbox.intersects(hitbox))
        {
            if (game.player.direction == direction || direction == Direction.Any)
            {
                result = true;
            }
        }
        game.player.hitbox.x = game.player.hitbox_default_x;
        game.player.hitbox.y = game.player.hitbox_default_y;
        hitbox.x = hitbox_default_x;
        hitbox.y = hitbox_default_y;

        return result;
    }
}