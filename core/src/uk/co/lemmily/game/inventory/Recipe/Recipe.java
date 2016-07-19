package uk.co.lemmily.game.inventory.Recipe;

import uk.co.lemmily.game.inventory.ItemSlot;

/**
 * Created by Emily on 26/10/2014.
 */
public interface Recipe {


    public ItemSlot getResult();

    public Byproduct getByproduct();

    public String getRecipeString();

    public void setByproduct( Byproduct pByp );
}
