package uk.co.lemmily.game.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emily on 29/10/2014.
 */
public class GameObject {

    public static char NEXT_ID = '"' - 1; //one before because nextID incrememnts first
    public static Map<String, GameObject> items = new HashMap<String, GameObject>();



    public static GameObject IRON_ORE = new GameObject("ironore", "resource");
    public static GameObject COPPER_ORE = new GameObject("copperore", "resource");
    public static GameObject COAL = new GameObject("coal", "resource");
    public static GameObject STONE = new GameObject("stone", "resource");
    public static GameObject SLAG = new GameObject("slag", "resource");
    public static GameObject IRON_BAR = new GameObject("ironbar", "resource");
    public static GameObject COPPER_PLATE = new GameObject("copperplate", "resource");
    public static GameObject COPPER_WIRE = new GameObject("copperwire", "resource");
    public static GameObject CIRCUIT_BOARD = new GameObject("circuitboard", "resource");
    public static GameObject NOTHING = new GameObject("nothing", ' ');


    protected char id;
    protected int maxNum; //how many you can have in one stack
    protected String textureRegion;
    protected String category;



    public GameObject() {
        this("default",'!', "default", 25);
    }
    public GameObject(String textureRegion) {
        this(textureRegion, '!', "default", 25);
    }
    public GameObject(String textureRegion, String category) {
        this(textureRegion, '!', category, 25);
    }
    public GameObject(String textureRegion, char id) {
        this(textureRegion, id, "default", 25);
    }

    public GameObject(String keyName, char id, String category, int maxNum) {
        if ( ! items.containsKey(keyName)){
            this.textureRegion = keyName;
            this.id = id == '!' ? getNextId() : id;
            this.category = category;
            this.maxNum = maxNum;
            items.put(keyName, this);
        } else {
            GameObject e = items.get(keyName);
            this.textureRegion = e.textureRegion;
            this.id = e.id;
            this.category = e.category;
            this.maxNum = e.maxNum;
        }
    }


    public static HashMap<String, GameObject> getItems() {
        return (HashMap<String, GameObject>)items;
    }

    public static void setItems(Map<String, GameObject> items) {
    GameObject.items = items;
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

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMaxNum() {
        return maxNum;
    }
}
