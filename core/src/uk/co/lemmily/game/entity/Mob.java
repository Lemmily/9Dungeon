package uk.co.lemmily.game.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emily on 01/11/2014.
 */
public class Mob extends GameObject{

    public static final Map<String, ObjectType> mobs = new HashMap<String, ObjectType>();


    protected int health;
    protected int evasion;
//    private int magicDamage;
//    private int magicRes;
    protected int damage;
    protected int armour;


    public Mob(ObjectType type) {
        objectType = type;

        health = 10;
        evasion = 1;
        damage = 3;
//        magicDamage = 10;
//        magicRes = 10;
        armour = 1;

        if( ! mobs.containsValue(objectType)) {
            mobs.put(objectType.getTextureRegion(), objectType);
        }
    }


    public void hurt(Mob mob, int dmg, int attackDir) {
    }
//
//    public void hurt( tile, int x, int y, int dmg) {
//    }

    public void hurt(ObjectType entity) {
    }

    public void takeDamage(int dmg) {
        this.health -= dmg;
    }

}
