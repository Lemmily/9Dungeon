package uk.co.lemmily.game.inventory.Recipe;

import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by emily on 19/07/16.
 */
public class Byproduct
{



	private float amount;
	private float chance;
	private ObjectType type;


	public Byproduct(float amount, float chance, ObjectType type) {
		this.amount = amount;
		this.chance = chance;
		this.type = type;
	}

	public float getAmount()
	{
		return amount;
	}

	public float getChance()
	{
		return chance;
	}

	public ObjectType getObjectType()
	{
		return type;
	}
}
