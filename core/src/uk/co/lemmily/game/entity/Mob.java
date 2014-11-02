package uk.co.lemmily.game.entity;

/**
 * Created by Emily on 01/11/2014.
 */
public class Mob extends Entity {

    protected int health;
    protected int evasion;
//    private int magicDamage;
//    private int magicRes;
    protected int damage;
    protected int armour;



    public Mob() {
        super();

        health = 10;
        evasion = 1;
        damage = 3;
//        magicDamage = 10;
//        magicRes = 10;
        armour = 1;
    }


    public void hurt(Mob mob, int dmg, int attackDir) {
    }
//
//    public void hurt( tile, int x, int y, int dmg) {
//    }

    public void hurt(Entity entity) {
    }

    public void takeDamage(int dmg) {
        this.health -= dmg;
    }
}
