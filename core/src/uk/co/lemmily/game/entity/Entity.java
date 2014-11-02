package uk.co.lemmily.game.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emily on 29/10/2014.
 */
public  class Entity {

    public static char NEXT_ID = '"' - 1; //one before because nextID incrememnts first
    public static Map<String, Entity> items = new HashMap<String, Entity>();



    public static Entity IRON_ORE = new Entity("ironore", "resource");
    public static Entity COPPER_ORE = new Entity("copperore", "resource");
    public static Entity COAL = new Entity("coal", "resource");
    public static Entity STONE = new Entity("stone", "resource");
    public static Entity SLAG = new Entity("slag", "resource");
    public static Entity IRON_BAR = new Entity("ironbar", "resource");
    public static Entity COPPER_PLATE = new Entity("copperplate", "resource");
    public static Entity COPPER_WIRE = new Entity("copperwire", "resource");
    public static Entity CIRCUIT_BOARD = new Entity("circuitboard", "resource");
    public static Entity NOTHING = new Entity("nothing", ' ');


    protected char id;
    protected String textureRegion;
    protected String category;



    public Entity() {
        this("default",'!', "default");
    }
    public Entity(String textureRegion) {
        this(textureRegion, '!', "default");
    }
    public Entity(String textureRegion, String category) {
        this(textureRegion, '!', category);
    }
    public Entity(String textureRegion, char id) {
        this(textureRegion, id, "default");
    }

    public Entity(String textureRegion, char id, String category) {
        if ( ! items.containsKey(textureRegion)){
            this.textureRegion = textureRegion;
            this.id = id == '!' ? getNextId() : id;
            this.category = category;
            items.put(textureRegion, this);
        } else {
            Entity e = items.get(textureRegion);
            this.textureRegion = e.textureRegion;
            this.id = e.id;
            this.category = e.category;

        }
    }


    public static HashMap<String, Entity> getItems() {
        return (HashMap<String, Entity>)items;
    }

    public static void setItems(Map<String, Entity> items) {
    Entity.items = items;
}



    public String getTextureRegion() {
        return textureRegion;
    }

    public static char getNextId() {
        NEXT_ID++;
        return NEXT_ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public char getId() {
        return id;
    }

    public String toString() {
        //todo: possibly get description hooked up here.
        return category + "[" + textureRegion + " : " + id + "]";
    }
}
