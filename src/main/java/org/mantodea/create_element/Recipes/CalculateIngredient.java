package org.mantodea.create_element.Recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;

public class CalculateIngredient extends AbstractIngredient {

    public int val;

    public int compare;

    public String nbtID;

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return new CalculateIngredientSerializer();
    }

    @Override
    public boolean test(ItemStack stack) {
        if(compare == 0)
            return stack.getOrCreateTag().getInt(nbtID) <= val;
        return stack.getOrCreateTag().getInt(nbtID) >= val;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", "CraftingHelper.getID()");
        json.addProperty("val", val);
        return json;
    }
}
