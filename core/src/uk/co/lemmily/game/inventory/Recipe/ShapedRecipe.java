package uk.co.lemmily.game.inventory.Recipe;

import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.inventory.ItemSlot;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Emily on 26/10/2014.
 */
public class ShapedRecipe implements Recipe {
    private Byproduct byproduct;
    private Map<Character, Integer> ingredients = new HashMap<Character, Integer>() {
    };
    private ItemSlot output;
    private String recipeString = "";


    public ShapedRecipe(ObjectType item, int num, String recipe) {
        this(new ItemSlot(item, num), recipe);
    }

    public ShapedRecipe(ItemSlot output, Byproduct byproducts, String recipe)
    {
        this.output = output;
        byproduct = byproducts;
        recipeString = recipe;
    }
    public ShapedRecipe(ItemSlot slot, String recipe) {
        output = slot;
        recipeString = recipe;
//        try {
//            recipeString += shape[0].length() + "x" + shape.length;
//
//            int i = 0;
//            int j = 0;
//            for (String row : shape) {
//                for (Character c : row.toCharArray()) {
//                    recipeString += c;
//                    pattern[i][j] = c;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

//    public ShapedRecipe () {
//        output = new Slot(null, 0);
//    }


    public Map<Character, Integer> getIngredients() {
        return ingredients;
    }

    public String getRecipeString() {
        return recipeString;
    }

    @Override
    public void setByproduct( Byproduct pByp )
    {
        this.byproduct = pByp;
    }

    @Override
    public ItemSlot getResult() {
        return output.copy();
    }

    @Override
    public Byproduct getByproduct()
    {
        return byproduct;
    }
}
