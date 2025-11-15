package unus.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.util.ArrayList;

import unus.main.*;

public class Player extends Entity
{
    public ArrayList<Entity> inventory = new ArrayList<Entity>();
    public final int invetory_size_max = 20;
    public final int screen_x;
    public final int screen_y;
    public boolean attack_canceled = false;
    //public int keys = 0;
    private Control control;

    private int idle_counter = 0;
    //private int pixel_counter = 0;
    private int dialoque_counter = 60;

    public Player(Game game, Control control)
    {
        super(game);

        this.control = control; // keyboard listener for movement
        this.screen_x = (game.SCREEN_WIDTH / 2) - (game.TILE_SIZE / 2);
        this.screen_y = (game.SCREEN_HEIGHT / 2) - (game.TILE_SIZE / 2);

        initialize();
    }

    public void initialize()
    {
        hitbox = new Rectangle(8, 16, 32, 32);
        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
        world_x = game.TILE_SIZE * 23;
        world_y = game.TILE_SIZE * 21;
        direction = Direction.Down;
        life_max = 6;
        life = life_max;

        // Player Status
        speed = 4;
        level = 1;
        strength = 1; // the more strength, the more damage he gives
        dexterity = 1; // the more dexteriry, the less damage he takes
        experience = 0;
        next_level_experience = 5;
        coin = 0;
        weapon = new Sword(game);
        shield = new Shield(game);

        // the total attack value is calculated by strength and weapon
        attack = getAttack();
        // the total defense value is calculated by dexterity and shield
        defense = getDefense();

        setImages();
        setInventory();

        // increasing these number increases attack range (for cheating or different weapon types)
        //attack_hitbox.width = 36;
        //attack_hitbox.height = 36;
    }

    private void setImages()
    {
        getWalkingImages();
        getAttackImages();
    }

    private void setInventory()
    {
        inventory.add(weapon);
        inventory.add(shield);
        /*inventory.add(new Key(game));
        inventory.add(new Key(game));
        inventory.add(new Lantern(game));
        inventory.add(new Key(game));
        inventory.add(new Coin(game));
        inventory.add(new Coin(game));
        inventory.add(new BlueShield(game));
        inventory.add(new Pick(game));
        inventory.add(new Axe(game));
        inventory.add(new RedPotion(game));
        inventory.add(new Boot(game));*/
    }

    private void getWalkingImages()
    {
        up0 = setup("/resources/image/player/up0.png");
        up1 = setup("/resources/image/player/up1.png");
        down0 = setup("/resources/image/player/down0.png");
        down1 = setup("/resources/image/player/down1.png");
        right0 = setup("/resources/image/player/right0.png");
        right1 = setup("/resources/image/player/right1.png");
        left0 = setup("/resources/image/player/left0.png");
        left1 = setup("/resources/image/player/left1.png");
    }

    private void getAttackImages()
    {
        switch (weapon.type)
        {
            case Sword :
            {
                attack_up0 = setup("/resources/image/player/sword_up0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_up1 = setup("/resources/image/player/sword_up1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down0 = setup("/resources/image/player/sword_down0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down1 = setup("/resources/image/player/sword_down1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_right0 = setup("/resources/image/player/sword_right0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_right1 = setup("/resources/image/player/sword_right1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left0 = setup("/resources/image/player/sword_left0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left1 = setup("/resources/image/player/sword_left1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                break;
            }
            case Axe :
            {
                attack_up0 = setup("/resources/image/player/axe_up0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_up1 = setup("/resources/image/player/axe_up1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down0 = setup("/resources/image/player/axe_down0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down1 = setup("/resources/image/player/axe_down1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_right0 = setup("/resources/image/player/axe_right0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_right1 = setup("/resources/image/player/axe_right1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left0 = setup("/resources/image/player/axe_left0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left1 = setup("/resources/image/player/axe_left1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                break;
            }
            case Pick :
            {
                attack_up0 = setup("/resources/image/player/pick_up0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_up1 = setup("/resources/image/player/pick_up1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down0 = setup("/resources/image/player/pick_down0.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_down1 = setup("/resources/image/player/pick_down1.png", game.TILE_SIZE, game.TILE_SIZE * 2);
                attack_right0 = setup("/resources/image/player/pick_right0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_right1 = setup("/resources/image/player/pick_right1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left0 = setup("/resources/image/player/pick_left0.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                attack_left1 = setup("/resources/image/player/pick_left1.png", game.TILE_SIZE * 2, game.TILE_SIZE);
                break;
            }
        }
    }

    public int getAttack()
    {
        attack_hitbox = weapon.attack_hitbox;

        return attack = strength * weapon.attack_value;
    }

    public int getDefense()
    {
        return defense = dexterity * shield.defense_value;
    }

    public void grabItem(int index)
    {
        if (index != 999)
        {
            String text = "";

            if (inventory.size() != invetory_size_max)
            {
                Entity item = game.items[index];

                //System.out.println(item.type);

                if (item.type == Type.Item || item.type == Type.Sword || item.type == Type.Axe || item.type == Type.Pick || item.type == Type.Shield)
                {
                    inventory.add(item);
                    game.playSE(1);

                    text = String.format("%s added to invetory.", game.items[index].getClass().getSimpleName());
                }
            }
            else
            {
                text = "Your inventory is full.";
            }
            game.ui.addMessage(text);
            game.items[index] = null;
        }
    }

    public void contactNPC(int index)
    {
        if (game.control.enter_pressed == true)
        {
            if (index != 999)
            {
                attack_canceled = true;
                game.state = State.Dialoque;
                game.npc[index].speak();
            }
        }
        else if (game.control.space_pressed == true)
        {
            //game.playSE(8);
            attacking = true;
        }
        /*if (game.control.enter_pressed == true)
        {
            if (index != 999)
            {
                if (game.control.enter_pressed == true)
                {
                    game.state = State.Dialoque;
                    game.npc[index].speak();
                }
            }
            else
            {
                if (game.control.enter_pressed == true)
                {
                    attacking = true;
                }
            }
        }*/
    }

    public void contactMonster(int index)
    {
        if (index != 999)
        {
            if (invincible == false)
            {
                game.playSE(7);

                int damage = game.monsters[index].attack - defense;

                if (damage < 0)
                {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void attackMonster(int index)
    {
        if (index != 999)
        {
            if (game.monsters[index].invincible == false)
            {
                //game.playSE(6);
                int damage = attack - game.monsters[index].defense;

                if (damage < 0)
                {
                    damage = 0;
                }
                game.monsters[index].life -= damage;
                game.monsters[index].invincible = true;
                game.monsters[index].damageReaction();

                game.ui.addMessage(String.format("%d damage to %s!", damage, game.monsters[index].getClass().getSimpleName()));

                if (game.monsters[index].life <= 0)
                {
                    game.monsters[index].dying = true;
                    game.ui.addMessage(String.format("You killed the %s", game.monsters[index].getClass().getSimpleName()));
                    game.ui.addMessage(String.format("Experience + %d", game.monsters[index].experience));

                    experience += game.monsters[index].experience;

                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp()
    {
        if (experience >= next_level_experience)
        {
            ++level;
            next_level_experience = next_level_experience * 2;

            life_max += 2;
            ++strength;
            ++dexterity;

            attack = getAttack();
            defense = getDefense();

            game.playSE(9);

            game.state = State.Dialoque;
            game.ui.dialoque = String.format("You are level %d now.\nLife and attributes have increased.", level);
        }
    }

    private void attack()
    {
        ++sprite_counter;

        if (sprite_counter <= 5)
        {// show attack sprite 1 during the 1st 5 frames (0 - 5)
            sprite_number = 0;
        }

        if (sprite_counter > 5 && sprite_counter <= 25)
        {// show attack sprite 2 for the next 20 frames (5 - 25)
            sprite_number = 1;

            // save current world x, y and hitbox width and height
            int current_world_x = world_x;
            int current_world_y = world_y;
            int current_hitbox_width = hitbox.width;
            int current_hitbox_height = hitbox.height;

            switch (direction)
            {// adjust player's world x and for the attack
                case Up :
                {
                    world_y -= attack_hitbox.height; break;
                }
                case Down :
                {
                    world_y += attack_hitbox.height; break;
                }
                case Left :
                {
                    world_x -= attack_hitbox.width; break;
                }
                case Right:
                {
                    world_x += attack_hitbox.width; break;
                }
            }
            // hitbox becomes attack hitbox
            hitbox.width = attack_hitbox.width;
            hitbox.height = attack_hitbox.height;
            // check monster collision with weapon (the new world x, y and hitbox)
            int index = game.bump.checkEntity(this, game.monsters);

            attackMonster(index);

            // after checking collision, reset to original values
            world_x = current_world_x;
            world_y = current_world_y;
            hitbox.width = current_hitbox_width;
            hitbox.height = current_hitbox_height;
        }

        if (sprite_counter > 25)
        {// reset
            sprite_number = 0;
            sprite_counter = 0;
            attacking = false;
        }
    }

    public void selectItem()
    {
        int index = game.ui.getItemSlotIndex();

        if (index < inventory.size())
        {
            Entity selected = inventory.get(index);

            if (selected.type == Type.Sword || selected.type == Type.Axe || selected.type == Type.Pick)
            {
                weapon = selected;
                attack = getAttack();
                getAttackImages();
            }

            if (selected.type == Type.Shield)
            {
                shield = selected;
                defense = getDefense();
            }

            if (selected.type == Type.Item)
            {
                selected.use(this);

                inventory.remove(index);
            }
        }
    }

    public void update()
    {
        if (attacking == true)
        {
            attack();
        }
        else if (control.getDirection() != Direction.Idle/* || control.enter_pressed == true*/)
        {
            this.direction = control.getDirection();

            collision = false;

            // check solid tile collision
            game.bump.checkTile(this);

            // check item collision
            int index = game.bump.checkItem(this, true);
            grabItem(index);

            // check NPC collision
            index = game.bump.checkEntity(this, game.npc);

            contactNPC(index);

            // check monster collision
            index = game.bump.checkEntity(this, game.monsters);

            contactMonster(index);

            // check events
            game.event.check();

            game.control.enter_pressed = false;

            if (collision == false /*&& control.enter_pressed == false*/)
            { // if no collision, player can move
                switch (this.direction)
                {
                    case Right :
                    {
                        world_x += speed; break;
                    }
                    case Left :
                    {
                        world_x -= speed; break;
                    }
                    case Up :
                    {
                        world_y -= speed; break;
                    }
                    case Down :
                    {
                        world_y += speed; break;
                    }
                }
            }

            if (control.enter_pressed == true && attack_canceled == false)
            {
                attacking = true;
                sprite_counter = 0;
            }
            attack_canceled = false;
            game.control.enter_pressed = false;
            game.control.space_pressed = false;

            sprite_counter++;

            if (sprite_counter > 14)
            {
                sprite_number = (sprite_number == 0) ? 1 : 0;
                sprite_counter = 0;
            }
            /*pixel_counter += speed;

            if (pixel_counter == 46)
            {
                direction = Direction.Idle;

                pixel_counter = 0;
            }*/
        }
        else
        {
            ++idle_counter;

            if (idle_counter > 30)
            {
                sprite_number = 0;
                idle_counter = 0;
            }
        }

        if (invincible == true)
        {
            ++invincible_counter;

            if (invincible_counter > 60)
            {
                invincible = false;
                invincible_counter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d)
    {
        BufferedImage image = null;
        int temp_screen_x = screen_x;
        int temp_screen_y = screen_y;

        switch (direction)
        {
            case Right :
            {
                if (attacking == true)
                {
                    image = (sprite_number == 0) ? attack_right0 : attack_right1;
                }
                else
                {
                    image = (sprite_number == 0) ? right0 : right1;
                }
                break;
            }
            case Left :
            {
                if (attacking == true)
                {
                    temp_screen_x = screen_x - game.TILE_SIZE;

                    image = (sprite_number == 0) ? attack_left0 : attack_left1;
                }
                else
                {
                    image = (sprite_number == 0) ? left0 : left1;
                }
                break;
            }
            case Up :
            {
                if (attacking == true)
                {
                    temp_screen_y = screen_y - game.TILE_SIZE;

                    image = (sprite_number == 0) ? attack_up0 : attack_up1;
                }
                else
                {
                    image = (sprite_number == 0) ? up0 : up1;
                }
                break;
            }
            case Down :
            {
                if (attacking == true)
                {
                    image = (sprite_number == 0) ? attack_down0 : attack_down1;
                }
                else
                {
                    image = (sprite_number == 0) ? down0 : down1;
                }
                break;
            }
        }

        if (invincible == true)
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2d.drawImage(image, temp_screen_x, temp_screen_y, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2d.setColor(Color.GREEN);
        g2d.drawRect(screen_x + hitbox.x, screen_y + hitbox.y, hitbox.width, hitbox.height);
    }
}