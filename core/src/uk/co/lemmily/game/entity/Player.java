package uk.co.lemmily.game.entity;

import uk.co.lemmily.game.PlayerInputListener;

/**
 * Created by Emily on 31/10/2014.
 */
public class Player extends Mob {

    private PlayerInputListener input = new PlayerInputListener();

    public Player() {
        health = 50;
        damage = 10;
        armour = 1;
        evasion = 2;
    }

    public void interact(GameObject entity) {
        System.out.println("player interacted with " + entity);
        if (entity instanceof Mob && ! (entity instanceof Player)) {
            hurt((Mob)entity);
        }
    }

    public void hurt(GameObject entity) {
        if (entity instanceof Mob) {
            Mob mob = (Mob) entity;

            mob.takeDamage(Math.max(0, damage - mob.armour));
        }
    }


    public int getDamage() {
        return damage;
    }
}
