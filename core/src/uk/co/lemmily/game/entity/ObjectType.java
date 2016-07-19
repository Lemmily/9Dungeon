package uk.co.lemmily.game.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emily on 29/10/2014.
 */
public class ObjectType {

    public static char NEXT_ID = '"' - 1; //one before because nextID increments first
    public static Map<String, ObjectType> items = new HashMap<String, ObjectType>();


    //these need to go elsewhere
    public static ObjectType IRON_ORE = new ObjectType("ironore", "resource");
    public static ObjectType COPPER_ORE = new ObjectType("copperore", "resource");
    public static ObjectType COAL = new ObjectType("coal", "resource");
    public static ObjectType STONE = new ObjectType("stone", "resource");
    public static ObjectType SLAG = new ObjectType("slag", "resource");
    public static ObjectType IRON = new ObjectType("iron", "resource");
    public static ObjectType COPPER_PLATE = new ObjectType("copper", "resource");
    public static ObjectType COPPER_WIRE = new ObjectType("copperwire", "resource");
    public static ObjectType CIRCUIT_BOARD = new ObjectType("circuitboard", "resource");

    public static  ObjectType GOBLIN = new ObjectType("goblin", "monster");

    //though this one can stay here
    public static ObjectType NOTHING = new ObjectType("nothing", ' ');


    protected char id;
    protected int maxNum; //how many you can have in one stack
    protected String textureRegion;
    protected String category;
    protected String keyName;



    public ObjectType() {
        this("default",'!', "default", 25);
    }
    public ObjectType(String textureRegion) {
        this(textureRegion, '!', "default", 25);
    }
    public ObjectType(String textureRegion, String category) {
        this(textureRegion, '!', category, 25);
    }
    public ObjectType(String textureRegion, String category, int maxNum) {
        this(textureRegion, '!', category, maxNum);
    }
    public ObjectType(String textureRegion, char id) {
        this(textureRegion, id, "default", 25);
    }

    public ObjectType(String keyName, char id, String category, int maxNum) {
        if ( ! items.containsKey(keyName)){
            this.textureRegion = keyName;
            this.id = id == '!' ? getNextId() : id;
            this.category = category;
            this.maxNum = maxNum;
            this.keyName = keyName;
            items.put(keyName, this);
        } else {
            ObjectType e = items.get(keyName);
            this.textureRegion = e.textureRegion;
            this.id = e.id;
            this.category = e.category;
            this.maxNum = e.maxNum;
            this.keyName = e.keyName;
        }
    }


    public static HashMap<String, ObjectType> getItems() {
        return (HashMap<String, ObjectType>)items;
    }

    public static void setItems(Map<String, ObjectType> items) {
    ObjectType.items = items;
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

    public String getKeyName() {
        return keyName;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMaxNum() {
        return maxNum;
    }
}
