package unus.main;

import java.awt.Rectangle;

public class Event
{
    boolean can_enter_event = true;
    int previous_event_x = 0;
    int previous_event_y = 0;

    Game game;
    EventRectangle[][] events;

    public Event(Game game)
    {
        this.game = game;

        initialize();
    }

    private void initialize()
    {
        events = new EventRectangle[game.WORLD_COLUMNS][game.WORLD_ROWS];
        /*hitbox = new Rectangle(23, 23, 2, 2);
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;*/
        int column = 0;
        int row = 0;

        while (column < game.WORLD_COLUMNS && row < game.WORLD_ROWS)
        {
            events[column][row] = new EventRectangle();
            events[column][row].x = 23;
            events[column][row].y = 23;
            events[column][row].width = 2;
            events[column][row].height = 2;
            events[column][row].hitbox_default_x = events[column][row].x;
            events[column][row].hitbox_default_y = events[column][row].y;

            column++;

            if (column == game.WORLD_COLUMNS)
            {
                column = 0;
                row++;
            }
        }
    }

    public void damagePit()
    {
        game.state = State.Dialoque;
        game.ui.dialoque = "Danger!\n\nThere is a pit here. You have taken damage.";
        --game.player.life;

        can_enter_event = false;
    }

    public void healingPool()
    {
        if (game.control.enter_pressed)
        {
            game.state = State.Dialoque;
            
            if (game.player.life < game.player.life_max)
            {
                game.player.life = game.player.life_max;
                game.ui.dialoque = "Your damage has been restored.";
            }
            else
            {
                game.ui.dialoque = "You have no damage to restore.";
            }
        }
    }

    public void teleport()
    {
        game.state = State.Dialoque;
        game.ui.dialoque = "You have been teleported to a new location!";
        game.player.world_x = game.TILE_SIZE * 37;
        game.player.world_y = game.TILE_SIZE * 10;
    }

    public void check()
    {
        int distance_x = Math.abs(game.player.world_x - previous_event_x);
        int distance_y = Math.abs(game.player.world_y - previous_event_y);
        int distance = Math.max(distance_x, distance_y);

        if (distance > game.TILE_SIZE)
        {// reset previous event for future interaction with player
            can_enter_event = true;
        }

        if (can_enter_event)
        {
            if (hit(27, 16, Direction.Right))
            {
                //damagePit(27, 16, State.Dialoque);
                damagePit();
            }

            if (hit(23, 19, Direction.Any))
            {
                //damagePit(23, 19, State.Dialoque);
                damagePit();
            }

            if (hit(23, 12, Direction.Up))
            {
                healingPool();
            }

            if (hit(10, 40, Direction.Up))
            {
                teleport();
            }
        }
    }

    public boolean hit(int column, int row, Direction direction)
    {
        boolean result = false;

        game.player.hitbox.x = game.player.world_x + game.player.hitbox.x;
        game.player.hitbox.y = game.player.world_y + game.player.hitbox.y;
        events[column][row].x = column * game.TILE_SIZE + events[column][row].x;
        events[column][row].y = row * game.TILE_SIZE + events[column][row].y;

        if (game.player.hitbox.intersects(events[column][row]) && events[column][row].complete == false)
        {
            if (game.player.direction == direction || direction == Direction.Any)
            {
                result = true;

                // save this event's coordinates to reset later
                previous_event_x = game.player.world_x;
                previous_event_y = game.player.world_y;

                game.control.direction = Direction.Idle;
            }
        }
        game.player.hitbox.x = game.player.hitbox_default_x;
        game.player.hitbox.y = game.player.hitbox_default_y;
        events[column][row].x = events[column][row].hitbox_default_x;
        events[column][row].y = events[column][row].hitbox_default_y;

        return result;
    }
}