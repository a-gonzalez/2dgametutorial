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

    }

    public void setNPCs()
    {
        game.npc[0] = new Oldman(game);
        game.npc[0].world_x = game.TILE_SIZE * 21;
        game.npc[0].world_y = game.TILE_SIZE * 21;

        game.npc[1] = new Oldman(game);
        game.npc[1].world_x = game.TILE_SIZE * 12;
        game.npc[1].world_y = game.TILE_SIZE * 33;

        game.npc[2] = new Oldman(game);
        game.npc[2].world_x = game.TILE_SIZE * 38;
        game.npc[2].world_y = game.TILE_SIZE * 8;
    }
}