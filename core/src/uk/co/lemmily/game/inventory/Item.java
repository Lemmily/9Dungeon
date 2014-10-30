package uk.co.lemmily.game.inventory;

/**
 * Created by Emily on 23/10/2014.
 */
public enum Item {

    IRON_ORE("ironore"),
    COPPER_ORE("copperore"),
    COAL("coal"),
    STONE("stone"),
    SLAG("slag"),
    IRON_BAR("ironbar"),
    COPPER_PLATE("copperplate"),
    COPPER_WIRE("copperwire"),
    CIRCUIT_BOARD("circuitboard"),

    NOTHING("nothing", ' ');

    private String textureRegion;


    private char id;

    private static char NEXT_ID;

    private Item(String textureRegion) {
        this.textureRegion = textureRegion;
        id = getNEXT_ID();
    }
    private Item(String textureRegion, char id) {
        this.textureRegion = textureRegion;
        this.id = id;
    }


    /**
     *
     * @return name of the image used by this item. Also same as keyName in resources.xml
     */
    public String getTextureRegion() {
        return textureRegion;
    }


    /**
     *
     * @return character representation of this item.
     */
    public char getId() {
        return id;
    }

    /**
     * increments next id and returns it.
     * @return
     */
    public static char getNEXT_ID() {
        if (NEXT_ID < 'A') {
            //instantiated here as enums are instantiated before the static variable.
            NEXT_ID = 64; //make it one before the first character as we increment
        }
        NEXT_ID += 1;
        return NEXT_ID;

    }
}
