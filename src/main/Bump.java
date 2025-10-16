package unus.main;

import unus.entity.Entity;
import unus.item.OpenedChest;

public class Bump
{
    Game game;

    public Bump(Game game)
    {
        this.game = game;
    }

    public int checkItem(Entity entity, boolean player)
    {
        int result = 999;

        for (int index = 0; index < game.items.length; index++)
        {
            if (game.items[index] != null)
            { // get entity's hitbox position
                entity.hitbox.x = entity.world_x + entity.hitbox.x;
                entity.hitbox.y = entity.world_y + entity.hitbox.y;

                // get the item's hitbox position
                game.items[index].hitbox.x = game.items[index].world_x + game.items[index].hitbox.x;
                game.items[index].hitbox.y = game.items[index].world_y + game.items[index].hitbox.y;

                switch (entity.direction)
                { // simulating entiry's movement and check where it will be after it moves
                    case Up :
                    {
                        entity.hitbox.y -= entity.getSpeed();

                        if (entity.hitbox.intersects(game.items[index].hitbox))
                        {
                            if (game.items[index].solid/* && game.items[index].getClass() != OpenedChest.class*/)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            { // make sure only the player is interacting with items
                                result = index;
                            }
                        }
                        break;
                    }
                    case Down :
                    {
                        entity.hitbox.y += entity.getSpeed();

                        if (entity.hitbox.intersects(game.items[index].hitbox))
                        {
                            if (game.items[index].solid)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            { // make sure only the player interacts with items
                                result = index;
                            }
                        }
                        break;
                    }
                    case Right :
                    {
                        entity.hitbox.x += entity.getSpeed();

                        if (entity.hitbox.intersects(game.items[index].hitbox))
                        {
                            if (game.items[index].solid)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            { // make sure only the player is interacting with items
                                result = index;
                            }
                        }
                        break;
                    }
                    case Left :
                    {
                        entity.hitbox.x -= entity.getSpeed();
                        
                        if (entity.hitbox.intersects(game.items[index].hitbox))
                        {
                            if (game.items[index].solid)
                            {
                                entity.collision = true;
                            }

                            if (player)
                            { // make sure only the player is interacting with items
                                result = index;
                            }
                        }
                        break;
                    }
                } // reset values after check
                entity.hitbox.x = entity.hitbox_default_x;
                entity.hitbox.y = entity.hitbox_default_y;

                game.items[index].hitbox.x = game.items[index].hitbox_default_x;
                game.items[index].hitbox.y = game.items[index].hitbox_default_y;
            }
        }
        return result;
    }

    public void checkTile(Entity entity)
    {
        int left_world_x = entity.world_x + entity.hitbox.x;
        int right_world_x = entity.world_x + entity.hitbox.x + entity.hitbox.width;
        int top_world_y = entity.world_y + entity.hitbox.y;
        int bottom_world_y = entity.world_y + entity.hitbox.y + entity.hitbox.height;

        int left_column = left_world_x / game.TILE_SIZE;
        int right_column = right_world_x / game.TILE_SIZE;
        int top_row = top_world_y / game.TILE_SIZE;
        int bottom_row = bottom_world_y / game.TILE_SIZE;

        int tile_1, tile_2;

        switch (entity.direction)
        {
            case Up :
            {
                top_row = (top_world_y - entity.getSpeed()) / game.TILE_SIZE;
                tile_1 = game.background.map[left_column][top_row];
                tile_2 = game.background.map[right_column][top_row];

                if (game.background.tiles[tile_1].isSolid() || game.background.tiles[tile_2].isSolid())
                {
                    entity.collision = true;
                }
                break;
            }
            case Down :
            {
                bottom_row = (bottom_world_y + entity.getSpeed()) / game.TILE_SIZE;
                tile_1 = game.background.map[left_column][bottom_row];
                tile_2 = game.background.map[right_column][bottom_row];

                if (game.background.tiles[tile_1].isSolid() || game.background.tiles[tile_2].isSolid())
                {
                    entity.collision = true;
                }
                break;
            }
            case Left :
            {
                left_column = (left_world_x - entity.getSpeed()) / game.TILE_SIZE;
                tile_1 = game.background.map[left_column][top_row];
                tile_2 = game.background.map[left_column][bottom_row];

                if (game.background.tiles[tile_1].isSolid() || game.background.tiles[tile_2].isSolid())
                {
                    entity.collision = true;
                }
                break;
            }
            case Right :
            {
                right_column = (right_world_x + entity.getSpeed()) / game.TILE_SIZE;
                tile_1 = game.background.map[right_column][top_row];
                tile_2 = game.background.map[right_column][bottom_row];

                if (game.background.tiles[tile_1].isSolid() || game.background.tiles[tile_2].isSolid())
                {
                    entity.collision = true;
                }
                break;
            }
        }
    }
}