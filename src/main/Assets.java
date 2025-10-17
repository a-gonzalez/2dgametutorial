package unus.main;

import unus.entity.*;
import unus.item.*;

public class Assets
{// this class will handle all the game assets (door, boot, key, etc...)
    private Game game;

    public Assets(Game game)
    {
        this.game = game;
    }

    public void setItems()
    {
        game.items[0] = new Key(game);
        game.items[0].world_x = 20 * game.TILE_SIZE;
        game.items[0].world_y = 7 * game.TILE_SIZE;

        game.items[1] = new Key(game);
        game.items[1].world_x = 10 * game.TILE_SIZE;
        game.items[1].world_y = 40 * game.TILE_SIZE;

        game.items[2] = new Boot(game);
        game.items[2].world_x = 36 * game.TILE_SIZE;
        game.items[2].world_y = 41 * game.TILE_SIZE;

        /*game.items[3] = new Door(game);
        game.items[3].world_x = 10 * game.TILE_SIZE;
        game.items[3].world_y = 11 * game.TILE_SIZE;*/

        game.items[3] = new IronDoor(game);
        game.items[3].world_x = 12 * game.TILE_SIZE;
        game.items[3].world_y = 12 * game.TILE_SIZE;

        game.items[4] = new Chest(game);
        game.items[4].world_x = 12 * game.TILE_SIZE;
        game.items[4].world_y = 9 * game.TILE_SIZE;
    }

    public void setNPCs()
    {
        /*game.npc[0] = new Oldman(game);
        game.npc[0].world_x = game.TILE_SIZE * 21;
        game.npc[0].world_y = game.TILE_SIZE * 21;*/
    }
}