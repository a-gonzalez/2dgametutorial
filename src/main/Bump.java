package unus.main;

import unus.entity.Entity;

public class Bump
{
    Game game;

    public Bump(Game game)
    {
        this.game = game;
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