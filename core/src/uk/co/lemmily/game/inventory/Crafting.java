package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.inventory.Recipe.Recipe;
import uk.co.lemmily.game.inventory.Recipe.ShapedRecipe;
import uk.co.lemmily.game.utils.XMLResourceReader;

import java.util.HashMap;

/**
 * Created by Emily on 24/10/2014.
 */
public class Crafting {

    public ItemSlot result;
    private Array<ItemSlot> slots;
    private int numSlots;
    public int modulo;

    private HashMap<String, ObjectType> itemTypes = new HashMap<String, ObjectType>();
    private HashMap<String, Recipe> recipes = new HashMap<String, Recipe>();

    public Crafting(int numSlots, int modulo) {
        this.numSlots = numSlots;
        this.modulo = modulo;
        slots = new Array<ItemSlot>(numSlots);

        for (int i = 0; i < numSlots; i++) {
            slots.add(new ItemSlot(null, 0));
        }

        this.result = new ItemSlot(null, 0) {
            @Override
            public int getMaxAmount() {
                return 1000; //special slot allows more to stack here.
            }
        };


        //store items under their keynames.
        for (ObjectType item : ObjectType.getItems().values()) itemTypes.put(item.getTextureRegion(), item);
        generateRecipes();
    }


    public String getCraftingSequence() {
        String sequence = "";

        //do some magic
        for (ItemSlot slot : slots) {
            if (slot.getObjectType() != null) {
                sequence += String.valueOf(slot.getObjectType().getId());
            } else {
                sequence += " ";
            }
        }
        sequence = trimColumns(trimEmptyRows(sequence));

        return sequence;
    }

    /**
     * must be used before column trim.
     * @param sequence
     * @return
     */
    private String trimEmptyRows(String sequence) {
        String newSequence = "";
        int i;
        int j = 0;

        while (j < sequence.length()) {
            i = 1;
            for (int k = 0; k < modulo; k++) {
                 if ( sequence.charAt(j + k) != ' ') {
                     //if it hits a character that's not empty in this row.
                     i = -1; //flag it.
                     break;
                 }
            }
            if (i == -1) {
                //add the row because it contains a non-empty.
                newSequence += sequence.substring(j,j + modulo);
            }
            j += modulo;
        }
        return newSequence;
    }

    /**
     * only to be done after row trim - also injects dimension at beginning.
     * @param sequence
     * @return
     */
    public String trimColumns(String sequence) {
        int colLength = sequence.length() / modulo; // modulo allowed because rows are still unchanged.
        String newSequence = "";
        String[] rows = new String[colLength];

        for (int i1 = 0; i1 < rows.length; i1++) {
            rows[i1] = "";
        }

        if (colLength > 0) {
            int i;
            int j = 0;

            while (j < modulo) {
                i = 1;
                for (int k = 0; k < colLength; k++) {
                    if (sequence.charAt(j + k * modulo) != ObjectType.NOTHING.getId()) {
                        i = -1;
                    }
                }
                if (i == -1) {
                    //add the row because it contains a non-empty character.
                    for (int k = 0; k < colLength; k++) {
                        rows[k] += sequence.substring(j + k * modulo, j + k * modulo + 1);
                    }
                }
                j++;
            }

            newSequence = "" + rows[0].length() + "x" + colLength;
            for (String row : rows) {
                newSequence += row;
            }
        }
        return  newSequence;
    }

    public Array<ItemSlot> getSlots() {
        return slots;
    }

    public ItemSlot getRecipe(String craftingSequence) {

        //todo: destruction of all items in crafting window
        if ( recipes.containsKey(craftingSequence) ) {
            return recipes.get(craftingSequence).getResult();
        }
        return null;
    }

    public void generateRecipes() {
        HashMap<String, HashMap<String, Object>> newRecipes = new XMLResourceReader().getResources(Gdx.files.internal("resources\\resources.xml"));

        for (String item : newRecipes.keySet()) {

            HashMap<String, Object> structure = newRecipes.get(item);
            ObjectType.getItems().get(item).setMaxNum(Integer.parseInt(structure.get("maxNum").toString())); //add in the maxNum
            if (structure.get("category").equals("processed")) {
                System.out.println(item + " started");

//                Entity objectType = Entity.items.values().toArray(Entity[].class)[]
                ItemSlot slot = new ItemSlot(itemTypes.get(structure.get("keyName")), (int)Float.parseFloat(structure.get("output").toString()));


                String recipeString = convertToRecipeString(newRecipes.get(item).get("shape"));
                recipes.put(recipeString, new ShapedRecipe(slot, recipeString));
                System.out.println(item + " finished");
                System.out.println();
            }
        }
    }

    private String convertToRecipeString(Object object) {
        //shape
        //keys for shape info.
        HashMap<String, String> rawRecipe = (HashMap<String, String>) object;

        String shapeString = rawRecipe.remove("shape");
        if (shapeString == null) {
            System.out.println("shape null");
        }
        HashMap<String, ObjectType> keys = new HashMap<String, ObjectType>();

        for (String s : rawRecipe.keySet()) {
            keys.put(s, getItemFromKeyName(rawRecipe.get(s)));
        }
        String[] rows = shapeString.split(",");


        String recipeString = "" + rows[0].length() + "x" + rows.length;
        for (String row : rows) {
            for (char c : row.toCharArray()) {
                recipeString += keys.get("" + c).getId();
            }
        }

        return recipeString;
    }

    private ObjectType getItemFromKeyName(String keyName) {
        return itemTypes.get(keyName);
    }


    public void addRecipe(ItemSlot slot, Object[] components) {

    }

    public void clearSlots() {
        for (ItemSlot slot : slots) {
            slot.empty();
        }
    }
}
