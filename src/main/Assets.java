package unus.main;

import unus.entity.*;
import unus.monster.*;

public class Assets
{// this class will handle all the game assets (door, boot, key, etc...)
    private Game game;

    public Assets(Game game)
    {
        this.game = game;
    }

    public void setItems()
    {
        game.items[0] = new Chest(game);
        game.items[0].world_x = game.TILE_SIZE * 12;
        game.items[0].world_y = game.TILE_SIZE * 8;

        game.items[1] = new MetalDoor(game);
        game.items[1].world_x = game.TILE_SIZE * 12;
        game.items[1].world_y = game.TILE_SIZE * 12;

        game.items[2] = new Key(game);
        game.items[2].world_x = game.TILE_SIZE * 25;
        game.items[2].world_y = game.TILE_SIZE * 23;

        game.items[4] = new Coin(game);
        game.items[4].world_x = game.TILE_SIZE * 25;
        game.items[4].world_y = game.TILE_SIZE * 19;

        game.items[5] = new Axe(game);
        game.items[5].world_x = game.TILE_SIZE * 21;
        game.items[5].world_y = game.TILE_SIZE * 19;

        game.items[6] = new Pick(game);
        game.items[6].world_x = game.TILE_SIZE * 36;
        game.items[6].world_y = game.TILE_SIZE * 10;

        game.items[7] = new MetalShield(game);
        game.items[7].world_x = game.TILE_SIZE * 13;
        game.items[7].world_y = game.TILE_SIZE * 30;

        game.items[8] = new RedPotion(game);
        game.items[8].world_x = game.TILE_SIZE * 21;
        game.items[8].world_y = game.TILE_SIZE * 23;
    }

    public void setNPCs()
    {
        /*game.npc[0] = new Oldman(game);
        game.npc[0].world_x = game.TILE_SIZE * 21;
        game.npc[0].world_y = game.TILE_SIZE * 21;

        game.npc[1] = new Oldman(game);
        game.npc[1].world_x = game.TILE_SIZE * 12;
        game.npc[1].world_y = game.TILE_SIZE * 33;*/

        game.npc[2] = new Oldman(game);
        game.npc[2].world_x = game.TILE_SIZE * 38;
        game.npc[2].world_y = game.TILE_SIZE * 8;
    }

    public void setMonsters()
    {
        game.monsters[0] = new Greenslime(game);
        game.monsters[0].world_x = game.TILE_SIZE * 23;
        game.monsters[0].world_y = game.TILE_SIZE * 36;

        game.monsters[1] = new Redslime(game);
        game.monsters[1].world_x = game.TILE_SIZE * 24;
        game.monsters[1].world_y = game.TILE_SIZE * 34;

        game.monsters[2] = new Bat(game);
        game.monsters[2].world_x = game.TILE_SIZE * 12;
        game.monsters[2].world_y = game.TILE_SIZE * 9;

        game.monsters[3] = new Bat(game);
        game.monsters[3].world_x = game.TILE_SIZE * 12;
        game.monsters[3].world_y = game.TILE_SIZE * 10;

        game.monsters[4] = new Orc(game);
        game.monsters[4].world_x = game.TILE_SIZE * 12;
        game.monsters[4].world_y = game.TILE_SIZE * 33;

        game.monsters[5] = new Skeleton(game);
        game.monsters[5].world_x = game.TILE_SIZE * 36;
        game.monsters[5].world_y = game.TILE_SIZE * 33;
    }
}